package com.example.nutritrack10.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

//load image using glide
fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}