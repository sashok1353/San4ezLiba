package com.san4a4a.san4ezliba.data.appsflyer

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@ActivityRetainedScoped
class AppsFlyerData @Inject constructor(){
    private val _appsData = MutableSharedFlow<Map<String, Any?>?> (
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val appsData: SharedFlow<Map<String, Any?>?> = _appsData

    suspend fun emitAppsData(data: Map<String, Any?>?) {
        _appsData.emit(data)
    }
}