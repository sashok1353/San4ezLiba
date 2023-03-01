package com.san4a4a.san4ezliba.presentation.webview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.san4a4a.san4ezliba.data.local.PreferenceManager
import com.san4a4a.san4ezliba.utils.Cipher.encrypt
import javax.inject.Inject

class CustomWebViewClient @Inject constructor(
    private val activity: Activity,
    private val preferenceManager: PreferenceManager,
    private val gameActivity: Activity,
    private val prefName: String,
    private val extraName: String,
    private val attributesNamesList: List<String>
) : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        if (
            view?.title?.contains(encrypt(attributesNamesList[0])) == true
        ) {
            activity.startActivity(Intent(activity, gameActivity::class.java))
            activity.finish()
        } else {
            if (preferenceManager.getLinkFromPref(prefName)?.isEmpty() == true) {
                val isNotificationLaunchUrl = activity.intent.getBooleanExtra(
                    extraName,
                    false
                )
                if (!isNotificationLaunchUrl) {
                    preferenceManager.saveLinkToPref(prefName,url.toString())
                }
            }
        }
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url?.toString() ?: return false

        return try {
            if (url.startsWith(encrypt(attributesNamesList[1]))) {
                Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    activity.startActivity(this)
                }
            }
            when {
                url.startsWith(encrypt(attributesNamesList[2])) || url.startsWith(encrypt(attributesNamesList[3])) -> false
                else -> {
                    when {
                        url.startsWith(encrypt(attributesNamesList[4])) -> {
                            Intent(Intent.ACTION_SEND).apply {
                                type = encrypt(attributesNamesList[5])
                                putExtra(
                                    Intent.EXTRA_EMAIL, url.replace(encrypt(attributesNamesList[4]), "")
                                )
                                Intent.createChooser(this, encrypt(attributesNamesList[6])).run {
                                    activity.startActivity(this)
                                }
                            }
                        }
                        url.startsWith(encrypt(attributesNamesList[7])) -> {
                            Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse(url)
                                Intent.createChooser(this, encrypt(attributesNamesList[8])).run {
                                    activity.startActivity(this)
                                }
                            }
                        }
                    }

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    activity.startActivity(intent)
                    true
                }
            }
        } catch (e: Exception) {
            true
        }
    }
}