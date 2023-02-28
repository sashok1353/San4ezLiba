package com.san4a4a.san4ezliba.domain.use_cases

import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.san4a4a.san4ezliba.data.appsflyer.AppsFlyerConversionListener
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InitAppsUseCase @Inject constructor(@ApplicationContext private val context: Context,
private val conversionListener: AppsFlyerConversionListener) {
    operator fun invoke(appsId: String) {
        AppsFlyerLib.getInstance()
            .init(appsId, conversionListener.conversionListener, context)
            .start(context)
    }
}