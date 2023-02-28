package com.san4a4a.san4ezliba.domain.use_cases

import android.content.Context
import com.appsflyer.AppsFlyerLib
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetAppsIdUseCase @Inject constructor(@ApplicationContext private val context: Context) {
    operator fun invoke() : String? {
        return AppsFlyerLib.getInstance().getAppsFlyerUID(context)
    }
}