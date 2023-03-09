package com.san4a4a.san4ezliba.domain.use_cases

import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.san4a4a.san4ezliba.data.appsflyer.AppsFlyerConversionListener
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InitAppsUseCase @Inject constructor(@ApplicationContext private val context: Context,
private val conversionListener: AppsFlyerConversionListener) {
    suspend operator fun invoke(appsId: String) {
        withContext(Dispatchers.IO) {
            AppsFlyerLib.getInstance()
                .init(appsId, conversionListener.conversionListener, context)
                .start(context)
        }
    }
}