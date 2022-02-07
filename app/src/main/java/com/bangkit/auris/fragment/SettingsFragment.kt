package com.bangkit.auris.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import com.bangkit.auris.DetailDictionaryActivity
import com.bangkit.auris.R
import com.bangkit.auris.databinding.FragmentSettingsBinding
import java.util.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private lateinit var binding: FragmentSettingsBinding

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.settingLanguage.setOnClickListener {
            Toast.makeText(this.requireActivity(), "language", Toast.LENGTH_SHORT).show()
            showDialog()
        }
    }

    fun showDialog() {
        val dialog = AlertDialog.Builder(this.requireActivity())
        val languages = arrayOf("Bahasa Indonesia", "English")
        val btn = getLanguage()

        dialog.setTitle(R.string.pick_language)
        dialog.setSingleChoiceItems(languages, btn,
            DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this.requireActivity(), languages[which], Toast.LENGTH_SHORT).show()
                changeLanguage(which)
                dialog.dismiss()
            })


        dialog.setNeutralButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        dialog.create()
        dialog.show()
    }

    fun changeLanguage(lang: Int) {
        // Languages
        val config = resources.configuration
//        val lang = "id" // your language code
        Log.d("bahasa lang code", lang.toString())
        var langCode = "in"

        if (lang == 0) {
            langCode = "in"
        }
        else if (lang == 1) {
            langCode = "en"
        }
//        if (lang.equals("english")) {
//            Log.d("bahasa", "English")
//            langCode = "en"
//        }

        val locale = Locale(langCode)
        Locale.setDefault(locale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            config.setLocale(locale)
        else
            config.locale = locale

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            context?.createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
        this.activity?.createConfigurationContext(config)
//        this.activity.setContentView()
    }

    fun getLanguage(): Int {
        val langCode = Locale.getDefault()

        Log.d("bahasa getLanguage", langCode.toString())

        return if (langCode.equals("in"))
            0
        else if (langCode.equals("en"))
            1
        else 1
    }




}