package com.san4a4a.san4ezliba.domain.use_cases

import javax.inject.Inject

class GetSubsListWithoutOneSubUseCase @Inject constructor(){
    operator fun invoke(campaign: String?, subs: List<String>?) : List<String>?{
        var subsArray: List<String>? = null
        if (campaign != null) {
            subsArray = if (subs != null && subs.size > 2) subs.slice(
                IntRange(0, 0) + IntRange(
                    2,
                    subs.lastIndex
                )
            ) else subs
        }
        return subsArray
    }
}