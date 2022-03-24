package com.bangkit.auris.utils

import android.content.res.Resources.getSystem
import android.os.Parcelable
import com.bumptech.glide.load.engine.Resource
import kotlinx.parcelize.Parcelize

data class DataDictionary(
    var title: String,
    var image: Int
)