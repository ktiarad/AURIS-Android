package com.bangkit.auris

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Camera
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.media.Image.Plane
import android.media.ImageReader
import android.media.ImageReader.OnImageAvailableListener
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Size
import android.view.Surface
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bangkit.auris.env.ImageUtils
import com.bangkit.auris.env.Logger
import com.bangkit.auris.fragment.CameraConnectionFragment
import com.bangkit.auris.fragment.LogoFragment
import com.bangkit.auris.viewmodel.PageViewModel
import com.bangkit.auris.viewmodel.ViewModelFactory
import com.bangkit.auris.R

abstract class CameraActivity : AppCompatActivity(), OnImageAvailableListener,
        Camera.PreviewCallback, CompoundButton.OnCheckedChangeListener, View.OnClickListener,
    CameraConnectionFragment.ConnectionCallback {
    //viewModel
    private lateinit var pageViewModel: PageViewModel
    protected var previewWidth = 0
    protected var previewHeight = 0
    val isDebug = false
    private var handler: Handler? = null
    private var handlerThread: HandlerThread? = null
    private var useCamera2API: Boolean = false
    private var isProcessingFrame = false
    private val yuvBytes = arrayOfNulls<ByteArray>(3)
    private var rgbBytes: IntArray? = null
    private var luminanceStride: Int = 0
    private var postInferenceCallback: Runnable? = null
    private var imageConverter: Runnable? = null

    protected val screenOrientation: Int
        get() {
            when (windowManager.defaultDisplay.rotation) {
                Surface.ROTATION_270 -> return 270
                Surface.ROTATION_180 -> return 180
                Surface.ROTATION_90 -> return 90
                else -> return 0
            }
        }

    protected abstract val layoutId: Int

    protected abstract val desiredPreviewFrameSize: Size

    protected fun getRgbBytes(): IntArray? {
        imageConverter!!.run()
        return rgbBytes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)

        //Instantiate ViewModel with ViewModelFactory
        this.pageViewModel = ViewModelFactory().createViewModel(this, application, PageViewModel::class.java)
    }

    /** Callback for android.hardware.Camera API  */
    override fun onPreviewFrame(bytes: ByteArray, camera: Camera) {
        if (isProcessingFrame) {
            LOGGER.w("Dropping frame!")
            return
        }

        try {
            // Initialize the storage bitmaps once when the resolution is known.
            if (rgbBytes == null) {
                val previewSize = camera.parameters.previewSize
                previewHeight = previewSize.height
                previewWidth = previewSize.width
                rgbBytes = IntArray(previewWidth * previewHeight)
                onPreviewSizeChosen(Size(previewSize.width, previewSize.height), 90)
            }
        } catch (e: Exception) {
            LOGGER.e(e, "Exception!")
            return
        }

        isProcessingFrame = true
        yuvBytes[0] = bytes
        luminanceStride = previewWidth

        imageConverter = Runnable { ImageUtils.convertYUV420SPToARGB8888(bytes, previewWidth, previewHeight, rgbBytes!!) }

        postInferenceCallback = Runnable {
            camera.addCallbackBuffer(bytes)
            isProcessingFrame = false
        }

        this.pageViewModel.getProcessingStatus().observe(this, {
            if (it == true) {
                processImage()
            }
        })
    }

    /** Callback for Camera2 API  */
    override fun onImageAvailable(reader: ImageReader) {
        // We need wait until we have some size from onPreviewSizeChosen

        if (previewWidth == 0 || previewHeight == 0) {
            return
        }
        if (rgbBytes == null) {
            rgbBytes = IntArray(previewWidth * previewHeight)
        }
        try {
            val image = reader.acquireLatestImage() ?: return

            if (isProcessingFrame) {
                image.close()
                return
            }

            isProcessingFrame = true
            Trace.beginSection("imageAvailable")
            val planes = image.planes
            fillBytes(planes, yuvBytes)
            luminanceStride = planes[0].rowStride
            val uvRowStride = planes[1].rowStride
            val uvPixelStride = planes[1].pixelStride

            imageConverter = Runnable {
                ImageUtils.convertYUV420ToARGB8888(
                    yuvBytes[0]!!,
                    yuvBytes[1]!!,
                    yuvBytes[2]!!,
                    previewWidth,
                    previewHeight,
                    luminanceStride,
                    uvRowStride,
                    uvPixelStride,
                    rgbBytes!!)
            }

            postInferenceCallback = Runnable {
                image.close()
                isProcessingFrame = false
            }

            processImage()
        } catch (e: Exception) {
            LOGGER.e(e, "Exception!")
            Trace.endSection()
            return
        }

        Trace.endSection()
    }

    @Synchronized
    public override fun onStart() {
        LOGGER.d("onStart $this")
        super.onStart()
    }

    @Synchronized
    public override fun onResume() {
        LOGGER.d("onResume $this")
        super.onResume()

        handlerThread = HandlerThread("inference")
        handlerThread!!.start()
        handler = Handler(handlerThread!!.looper)
    }

    @Synchronized
    public override fun onPause() {
        LOGGER.d("onPause $this")

        handlerThread!!.quitSafely()
        try {
            handlerThread!!.join()
            handlerThread = null
            handler = null
        } catch (e: InterruptedException) {
            LOGGER.e(e, "Exception!")
        }

        super.onPause()
    }

    @Synchronized
    public override fun onStop() {
        LOGGER.d("onStop $this")
        super.onStop()
    }

    @Synchronized
    public override fun onDestroy() {
        LOGGER.d("onDestroy $this")
        super.onDestroy()
    }

    @Synchronized
    protected fun runInBackground(r: Runnable) {
        if (handler != null) {
            handler!!.post(r)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST) {
            if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                setFragment()
            } else {
                requestPermission()
            }
        }
    }

    fun hasPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSelfPermission(PERMISSION_CAMERA) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(PERMISSION_STORAGE) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (shouldShowRequestPermissionRationale(PERMISSION_CAMERA)) {
                Toast.makeText(
                    this@CameraActivity,
                    "Camera permission is required for this demo",
                    Toast.LENGTH_LONG)
                    .show()
            }
            requestPermissions(arrayOf(PERMISSION_CAMERA, PERMISSION_STORAGE), PERMISSIONS_REQUEST)
        }
    }

    // Returns true if the device supports the required hardware level, or better.
    private fun isHardwareLevelSupported(
        characteristics: CameraCharacteristics, requiredLevel: Int): Boolean {
        val deviceLevel = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)!!
        return if (deviceLevel == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
            requiredLevel == deviceLevel
        } else requiredLevel <= deviceLevel
        // deviceLevel is not LEGACY, can use numerical sort
    }

    private fun chooseCamera(): String? {
        val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            for (cameraId in manager.cameraIdList) {
                val characteristics = manager.getCameraCharacteristics(cameraId)

                // We don't use a front facing camera in this sample.
                val facing = characteristics.get(CameraCharacteristics.LENS_FACING)
                if (facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT) {
                    continue
                }

                // Fallback to camera1 API for internal cameras that don't have limited support.
                // This should help with legacy situations where using the camera2 API causes
                // distorted or otherwise broken previews.
                useCamera2API = facing == CameraCharacteristics.LENS_FACING_EXTERNAL || isHardwareLevelSupported(
                    characteristics, CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED)
                LOGGER.i("Camera API lv2?: %s", useCamera2API)
                return cameraId
            }
        } catch (e: CameraAccessException) {
            LOGGER.e(e, "Not allowed to access camera")
        }

        return null
    }

    fun setFragment() {
        val cameraId = chooseCamera()
        val fragment: Fragment

        if (useCamera2API) {
            val camera2Fragment = CameraConnectionFragment.newInstance(
                object : CameraConnectionFragment.ConnectionCallback {
                    override fun onPreviewSizeChosen(size: Size, cameraRotation: Int) {
                        previewHeight = size.height
                        previewWidth = size.width
                        this@CameraActivity.onPreviewSizeChosen(size, cameraRotation)
                    }
                },
                this,
                layoutId,
                desiredPreviewFrameSize)

            if (cameraId != null) {
                camera2Fragment.setCamera(cameraId)
            }
            fragment = camera2Fragment
        } else {
            Toast.makeText(
                this@CameraActivity,
                "Your device doesn't support Camera API 2",
                Toast.LENGTH_LONG)
                .show()
            fragment = LogoFragment.newInstance()
        }

        supportFragmentManager.beginTransaction().replace(R.id.camera_container, fragment).commit()
    }

    protected fun fillBytes(planes: Array<Plane>, yuvBytes: Array<ByteArray?>) {
        // Because of the variable row stride it's not possible to know in
        // advance the actual necessary dimensions of the yuv planes.
        for (i in planes.indices) {
            val buffer = planes[i].buffer
            if (yuvBytes[i] == null) {
                LOGGER.d("Initializing buffer %d at size %d", i, buffer.capacity())
                yuvBytes[i] = ByteArray(buffer.capacity())
            }
            buffer.get(yuvBytes[i])
        }
    }

    protected fun readyForNextImage() {
        if (postInferenceCallback != null) {
            postInferenceCallback!!.run()
        }
    }

    protected abstract fun processImage()
    abstract override fun onPreviewSizeChosen(size: Size, cameraRotation: Int)
    protected abstract fun setNumThreads(numThreads: Int)
    protected abstract fun setUseNNAPI(isChecked: Boolean)

    companion object {
        private val LOGGER = Logger()
        private const val PERMISSIONS_REQUEST = 1
        private const val PERMISSION_CAMERA = Manifest.permission.CAMERA
        private const val PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
    }
}
