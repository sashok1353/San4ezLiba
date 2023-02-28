package com.san4a4a.san4ezliba.domain.use_cases

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetBatteryLevelUseCase @Inject constructor(@ApplicationContext private val context: Context) {
    operator fun invoke() : Double{
        return try {
            val batteryIntent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
            val batteryLevel = batteryIntent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
            batteryLevel.toDouble()
        } catch (e: Exception) {
            100.0
        }
    }
}