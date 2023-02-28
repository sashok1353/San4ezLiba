package com.san4a4a.san4ezliba.presentation

import com.san4a4a.san4ezliba.utils.Cipher.encrypt
import java.net.URLEncoder

fun String.getDataFromMap(map: Map<String, Any?>?) : String?{
    return try {
        map?.get(encrypt(this)).toString()
    } catch (e: Exception) {
        null
    }
}

fun String?.encode(): String {
    return runCatching {
        URLEncoder.encode(this.toString(), "utf-8")
    }.getOrElse { "null" }
}