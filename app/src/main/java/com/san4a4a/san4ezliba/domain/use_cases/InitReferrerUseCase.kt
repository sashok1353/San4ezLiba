package com.san4a4a.san4ezliba.domain.use_cases

import android.content.Context
import android.util.Log
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class InitReferrerUseCase @Inject constructor(@ApplicationContext private val context: Context) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke() : String? =
        suspendCancellableCoroutine { continuation ->
            try {
                val referrerClient = InstallReferrerClient.newBuilder(context).build()
                referrerClient.startConnection(object : InstallReferrerStateListener {
                    override fun onInstallReferrerSetupFinished(responseCode: Int) {
                        when (responseCode) {
                            InstallReferrerClient.InstallReferrerResponse.OK -> try {
                                val response = referrerClient.installReferrer
                                val referrer = response.installReferrer
                                Log.d("Referrer", "Referrer: $referrer")
                                continuation.resume(referrer) {
                                    referrerClient.endConnection()
                                }
                            } catch (e: Exception) {
                                Log.e("REFERRER ERROR", "Exception occurred while getting the referrer")
                                continuation.resumeWithException(e)
                            }
                            InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                                Log.e("REFERRER ERROR", "API not available on the current Play Store app")
                                continuation.resume(null)
                            }
                            InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                                Log.e("REFERRER ERROR", "Connection couldn't be established")
                                continuation.resume(null)
                            }
                            InstallReferrerClient.InstallReferrerResponse.DEVELOPER_ERROR -> {
                                Log.e("REFERRER ERROR", "Developer error")
                                continuation.resume(null)
                            }
                            InstallReferrerClient.InstallReferrerResponse.SERVICE_DISCONNECTED -> {
                                Log.e("REFERRER ERROR", "Service disconnected")
                                continuation.resume(null)
                            }
                        }
                        referrerClient.endConnection()
                    }
                    override fun onInstallReferrerServiceDisconnected() {
                        Log.e("REFERRER DISCONNECTED", "Try to restart the connection on the next request")
                        continuation.resume(null)
                    }
                })
            } catch (e: Exception) {
                continuation.resume(null)
            }
        }
}