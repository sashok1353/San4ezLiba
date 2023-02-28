package com.san4a4a.san4ezliba.domain.use_cases

import javax.inject.Inject

class MakeSubsLineUseCase @Inject constructor(){
    operator fun invoke(subs: List<String>?) : String {
        val linkBuilder = StringBuilder()
        if(subs == null) {
            for(i in 1..11) {
                when(i) {
                    1 -> linkBuilder.append("sub${i}=null&")
                    else -> {
                        linkBuilder.append("sub${i}=&")
                    }
                }
            }
        } else {
            if(subs.size >= 10) {
                for(i in subs.indices) {
                    linkBuilder.append("sub${i+1}=${subs[i]}&")
                }
            } else {
                for(i in subs.indices) {
                    linkBuilder.append("sub${i + 1}=${subs[i]}&")
                }
                for(i in subs.size..10) {
                    linkBuilder.append("sub${i+1}=&")
                }
            }
        }
        return linkBuilder.toString()
    }
}