package com.san4a4a.san4ezliba.domain.use_cases

import android.content.Context
import com.facebook.applinks.AppLinkData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetDeeplinkUseCase @Inject constructor(@ApplicationContext private val context: Context){
    suspend operator fun invoke(): String? {
        return suspendCoroutine{ cont ->
            AppLinkData.fetchDeferredAppLinkData(context) {
                cont.resume(it?.targetUri?.toString())
            }
        }
    }
}