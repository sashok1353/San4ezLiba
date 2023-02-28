package com.san4a4a.san4ezliba.presentation.webview

import android.annotation.SuppressLint
import android.os.Build
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView

fun WebView.fixForXiaomiDevices() {
    this.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            importantForAutofill = WebView.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        }
    }
}

fun WebView.setCookiesSettings() {
    this.apply {
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
        isSaveEnabled = true
        isFocusable = true
        isFocusableInTouchMode = true
        isVerticalScrollBarEnabled = false
        isHorizontalScrollBarEnabled = false
        setLayerType(WebView.LAYER_TYPE_HARDWARE, null)
    }
}

@SuppressLint("SetJavaScriptEnabled")
fun WebView.setSettings() {
    this.settings.apply {
        mixedContentMode = 0
        javaScriptEnabled = true
        domStorageEnabled = true
        loadsImagesAutomatically = true
        databaseEnabled = true
        useWideViewPort = true
        allowFileAccess = true
        javaScriptCanOpenWindowsAutomatically = true
        loadWithOverviewMode = true
        allowContentAccess = true
        setSupportMultipleWindows(false)
        builtInZoomControls = true
        displayZoomControls = false
        cacheMode = WebSettings.LOAD_DEFAULT
        userAgentString = userAgentString.replace("; wv","")
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) saveFormData = true
    }
}