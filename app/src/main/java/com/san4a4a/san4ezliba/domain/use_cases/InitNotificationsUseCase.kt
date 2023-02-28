package com.san4a4a.san4ezliba.domain.use_cases

import android.app.Activity
import android.content.Intent
import com.onesignal.OneSignal
import com.san4a4a.san4ezliba.utils.Cipher.encrypt
import javax.inject.Inject

class InitNotificationsUseCase @Inject constructor() {
    operator fun invoke(entryActivity: Activity, destinationActivity: Activity, startWithList: List<String>,intentExtraName: String,intentExtraFlagName: String) {
        OneSignal.setNotificationOpenedHandler {
            val launchUrl: String? = it?.notification?.launchURL
            if (launchUrl == null || launchUrl == "null" || launchUrl == "") {
                return@setNotificationOpenedHandler
            }

            val isValidUrl =
                launchUrl.startsWith(encrypt(startWithList[0])) ||
                        launchUrl.startsWith(encrypt(startWithList[1])) ||
                        launchUrl.startsWith(encrypt(startWithList[2]))


            if (isValidUrl) {
                // Remove this line if we use this in Application class
                OneSignal.setNotificationOpenedHandler(null)

                val intent = Intent(entryActivity, destinationActivity::class.java)
                intent.putExtra(intentExtraName, launchUrl)

                // This is used to prevent the app from caching the Launch URL
                intent.putExtra(intentExtraFlagName, true)

                entryActivity.startActivity(intent)
                entryActivity.finish()
            }
        }
    }
}