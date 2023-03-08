package com.san4a4a.san4ezliba.presentation

import android.util.Log
import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Window.enterFullScreen(view: View) {
    try {
        WindowCompat.setDecorFitsSystemWindows(this, true)
        WindowCompat.getInsetsController(this, view).let { mView ->
            mView.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            mView.hide(WindowInsetsCompat.Type.systemBars())
        }
    } catch (exp: Exception) {
        Log.i("Exception", "Exception while resizing window")
    }
}