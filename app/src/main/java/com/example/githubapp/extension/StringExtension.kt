package com.example.githubapp.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat

fun String.openUrl(context: Context) {
    try {
        val uri = Uri.parse(this)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        ContextCompat.startActivity(context, intent, null)
    } catch (e: Exception) {
        Log.e("openUrl", e.message.orEmpty(), e)
    }
}
