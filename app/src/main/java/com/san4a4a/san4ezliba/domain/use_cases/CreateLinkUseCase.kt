package com.san4a4a.san4ezliba.domain.use_cases

import com.san4a4a.san4ezliba.utils.Cipher.encrypt
import javax.inject.Inject

class CreateLinkUseCase @Inject constructor(
    private val makeSubsLine: MakeSubsLineUseCase
) {
    operator fun invoke(
        domain: String,
        subsList: List<String>?,
        attributesMap: Map<String, String>
    ) : String {
        val link = StringBuilder().apply {
            append(encrypt(domain))
            append(makeSubsLine(subsList))
            attributesMap.forEach { (key, value) ->
                if(encrypt(key) == "fb_at") {
                    append("${encrypt(key)}=$value")
                } else {
                    append("${encrypt(key)}=$value&")
                }
            }
        }
        return link.toString()
    }
}