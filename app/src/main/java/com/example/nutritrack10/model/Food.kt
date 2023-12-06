package com.example.nutritrack10.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
    var name: String = "",
    var cal: String = "",
    var photo: Int = 0
): Parcelable
