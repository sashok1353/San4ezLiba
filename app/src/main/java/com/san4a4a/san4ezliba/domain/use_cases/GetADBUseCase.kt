package com.san4a4a.san4ezliba.domain.use_cases

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetADBUseCase @Inject constructor(@ApplicationContext private val context: Context){
    operator fun invoke() : Boolean {
        return with(context.contentResolver) {
            android.provider.Settings.Global.getInt(this, android.provider.Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) == 1
        }
    }
}