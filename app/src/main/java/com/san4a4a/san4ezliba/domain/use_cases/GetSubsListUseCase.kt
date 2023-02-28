package com.san4a4a.san4ezliba.domain.use_cases

import javax.inject.Inject

class GetSubsListUseCase @Inject constructor(){
    operator fun invoke(campaign: String?, deeplink: String?) : List<String>? {
        val subs:List<String>? = try {
            when {
                deeplink != null && deeplink.isNotBlank() -> deeplink.split("://").getOrNull(1)?.split("_")
                campaign != null && campaign != "null" -> campaign.split("_")
                else -> null
            }
        } catch (e: Exception) {
            null
        }
        return subs?.takeIf { it.isNotEmpty() }
    }
}