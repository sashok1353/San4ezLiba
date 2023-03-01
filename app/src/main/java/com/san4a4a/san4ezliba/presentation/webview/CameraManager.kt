package com.san4a4a.san4ezliba.presentation.webview

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CameraManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private var takePictureWithUriReturnContract: TakePictureWithUriReturnContract,
    private val customWebChromeClient: CustomWebChromeClient,
    activity: Activity,
) {

    private val activity: ComponentActivity = activity as ComponentActivity

    companion object {
        private const val CAMERA_KEY = "Take photo from camera"
        private const val GALLERY_KEY = "Take photo from gallery"
    }

    private var galleryLauncher: ActivityResultLauncher<Array<String>>? = null
    private var cameraLauncher: ActivityResultLauncher<Uri>? = null

    init {
        initGalleryLauncher()
        initCameraLauncher()
    }

    private fun initGalleryLauncher() {
        galleryLauncher =
            activity.activityResultRegistry.register(GALLERY_KEY, ActivityResultContracts.OpenDocument()) { uri ->
                if (uri != null) {
                    customWebChromeClient.mFilePathCallback?.onReceiveValue(arrayOf(uri))
                } else {
                    customWebChromeClient.mFilePathCallback?.onReceiveValue(null)
                }
            }
    }

    private fun initCameraLauncher() {
        cameraLauncher = activity.activityResultRegistry.register(
            CAMERA_KEY,
            takePictureWithUriReturnContract
        ) { (success, imageUri) ->
            if (success) {
                customWebChromeClient.mFilePathCallback?.onReceiveValue(arrayOf(imageUri))
            } else {
                customWebChromeClient.mFilePathCallback?.onReceiveValue(null)
            }
        }
    }

    fun pickImgFromGallery() {
        galleryLauncher?.launch(arrayOf("image/*"))
    }

    private fun getTmpFileUri(activity: Activity, buildConfigName: String): Uri {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir: File? = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val img = File.createTempFile(imageFileName, ".jpg", storageDir)

        return FileProvider.getUriForFile(context, "${buildConfigName}.provider", img)
    }

    private fun takeImage(activity: Activity, buildConfigName: String) {
        getTmpFileUri(activity, buildConfigName).let { uri -> cameraLauncher?.launch(uri)}
    }

    fun showImageSourceSelectionDialog(activity: Activity, buildConfigName: String) {
        val options = arrayOf<CharSequence>("Take picture", "Pick from gallery", "Cancel")
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Choose image")
        builder.setCancelable(false)
        builder.setItems(options) { dialog, item ->
            when (item) {
                0 -> {
                    takeImage(activity, buildConfigName)
                }
                1 -> {
                    pickImgFromGallery()
                }
                2 -> {
                    customWebChromeClient.mFilePathCallback?.onReceiveValue(null)
                    dialog.dismiss()
                }

            }
        }
        builder.show()
    }
}