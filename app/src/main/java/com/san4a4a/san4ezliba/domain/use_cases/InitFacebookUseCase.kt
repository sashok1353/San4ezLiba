package com.san4a4a.san4ezliba.domain.use_cases

import android.content.Context
import com.facebook.FacebookSdk
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Suppress("DEPRECATION")
class InitFacebookUseCase @Inject constructor(@ApplicationContext private val context: Context) {
    operator fun invoke(facebookId: String, facebookToken: String) {
        FacebookSdk.apply {
            setApplicationId(facebookId)
            setClientToken(facebookToken)
            sdkInitialize(context)
            setAdvertiserIDCollectionEnabled(true)
            setAutoInitEnabled(true)
            fullyInitialize()
        }
    }
}