package com.example.githubapp.extension

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(context: Context, url: String?) {
    Glide.with(context).load(url).into(this)
}
