package com.san4a4a.san4ezliba.domain.use_cases

import android.content.Context
import com.onesignal.OneSignal
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InitOneSignalUseCase @Inject constructor(@ApplicationContext private val context: Context) {
    operator fun invoke(appId: String) {
        OneSignal.initWithContext(context)
        OneSignal.setAppId(appId)
        OneSignal.setLogLevel(
            OneSignal.LOG_LEVEL.VERBOSE,
            OneSignal.LOG_LEVEL.NONE
        )
    }
}