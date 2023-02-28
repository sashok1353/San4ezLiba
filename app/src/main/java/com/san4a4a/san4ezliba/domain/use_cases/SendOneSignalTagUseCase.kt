package com.san4a4a.san4ezliba.domain.use_cases

import com.onesignal.OneSignal
import com.san4a4a.san4ezliba.utils.Cipher.encrypt
import javax.inject.Inject

class SendOneSignalTagUseCase @Inject constructor(){
    operator fun invoke(appsId: String?, element: String?, nameList: List<String>) {
        OneSignal.setExternalUserId(appsId.toString())
        OneSignal.sendTag(
            encrypt(nameList[0]),
            (element ?: encrypt(nameList[1]))
        )
    }
}