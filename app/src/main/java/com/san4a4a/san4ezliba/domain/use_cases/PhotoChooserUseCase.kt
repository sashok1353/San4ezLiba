package com.san4a4a.san4ezliba.domain.use_cases

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoChooserUseCase @Inject constructor(){
    private val _photoChooser = MutableSharedFlow<Boolean>(0,1, BufferOverflow.DROP_OLDEST)
    val photoChooser: SharedFlow<Boolean> = _photoChooser

    suspend fun provideChooser(flag: Boolean) {
        _photoChooser.emit(flag)
    }
}