package com.san4a4a.san4ezliba.data.appsflyer

import com.appsflyer.AppsFlyerConversionListener
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppsFlyerConversionListener @Inject constructor(
    private val appsFlyerData: AppsFlyerData
){

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    val conversionListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            coroutineScope.launch {
                if(data.isNullOrEmpty()) {
                    appsFlyerData.emitAppsData(null)
                } else {
                    appsFlyerData.emitAppsData(data)
                }
            }
        }

        override fun onConversionDataFail(error: String?) {
            coroutineScope.launch {
                appsFlyerData.emitAppsData(null)
            }
        }

        override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
            coroutineScope.launch {
                appsFlyerData.emitAppsData(null)
            }
        }

        override fun onAttributionFailure(error: String?) {
            coroutineScope.launch {
                appsFlyerData.emitAppsData(null)
            }
        }
    }
}