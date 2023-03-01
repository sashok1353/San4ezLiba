package com.san4a4a.san4ezliba.presentation.webview

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Build
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.san4a4a.san4ezliba.domain.use_cases.PhotoChooserUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CustomWebChromeClient @Inject constructor(@ApplicationContext private val context: Context, private val photoChooser: PhotoChooserUseCase) : WebChromeClient() {

    var mFilePathCallback: ValueCallback<Array<Uri>>? = null

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        mFilePathCallback = filePathCallback
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            val permissions: ArrayList<String> = arrayListOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,)
            permissionHandler(permissions)
        } else {
            val permissions: ArrayList<String> = arrayListOf(Manifest.permission.CAMERA)
            permissionHandler(permissions)
        }
        return true
    }

    private fun permissionHandler(permissions: ArrayList<String> ) {
        Dexter.withContext(context)
            .withPermissions(permissions)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        CoroutineScope(Dispatchers.IO).launch {
                            photoChooser.provideChooser(flag = true)
                        }
                    } else {
                        CoroutineScope(Dispatchers.IO).launch {
                            photoChooser.provideChooser(flag = false)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).check()
    }
}