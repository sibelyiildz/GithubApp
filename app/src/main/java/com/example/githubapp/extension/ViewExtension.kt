package com.example.githubapp.extension

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(context: Context, url: String?) {
    Glide.with(context).load(url).into(this)
}

fun TextView.setTextAndUnderLineSpan(text: String?) {
    val spannableString = SpannableString(text)
    spannableString.setSpan(
        UnderlineSpan(),
        0,
        text.orEmpty().length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    this.text = spannableString
}
