package com.san4a4a.san4ezliba.presentation

import java.net.URLEncoder

fun String.getDataFromMap(map: Map<String, Any?>?) : String?{
    return try {
        map?.get(this).toString()
    } catch (e: Exception) {
        null
    }
}

private fun Any?.encode(): String {
    return runCatching {
        URLEncoder.encode(this.toString(), "utf-8")
    }.getOrElse { "null" }
}