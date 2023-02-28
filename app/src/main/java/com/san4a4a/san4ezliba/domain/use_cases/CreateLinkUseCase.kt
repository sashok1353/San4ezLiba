package com.san4a4a.san4ezliba.domain.use_cases

import com.san4a4a.san4ezliba.utils.Cipher.encrypt
import javax.inject.Inject

class CreateLinkUseCase @Inject constructor(
    private val makeSubsLine: MakeSubsLineUseCase
) {
    operator fun invoke(
        subsList: List<String>?,
        gaid: String?,
        campaign: String?,
        appsId: String?,
        adb: Boolean,
        battery: Double,
        push: String?,
        accountId: String?,
        afChannel: String?,
        campaignId: String?,
        adId: String?,
        adSet: String?,
        mediaSource: String?,
        afStatus: String?,
        afAd: String?,
        adsetId: String?,
        appsKey: String?,
        facebookId: String?,
        facebookToken: String
    ) : String {
        val link = StringBuilder().apply {
            append(encrypt("kwwsv://dydqfln.frp?"))
            append(makeSubsLine(subsList))
            append("${encrypt("fdpsdljq")}=$campaign&")
            append("${encrypt("dffrxqw_lg")}=$accountId&")
            append("${encrypt("jrrjoh_dglg")}=$gaid&")
            append("${encrypt("di_xvhulg")}=$appsId&")
            append("${encrypt("phgld_vrxufh")}=$mediaSource&")
            append("${encrypt("di_fkdqqho")}=$afChannel&")
            append("${encrypt("di_vwdwxv")}=$afStatus&")
            append("${encrypt("dge")}=$adb&")
            append("${encrypt("edwwhuB")}=$battery&")
            append("${encrypt("di_dg")}=$afAd&")
            append("${encrypt("fdpsdljq_lg")}=$campaignId&")
            append("${encrypt("dgvhw_lg")}=$adsetId&")
            append("${encrypt("dg_lg")}=$adId&")
            append("${encrypt("dgvhw")}=$adSet&")
            append("${encrypt("exqgoh")}=com.yoozoogames.fwga&")
            append("${encrypt("sxvk")}=$push&")
            append("${encrypt("ghy_nhB")}=$appsKey&")
            append("${encrypt("ie_dss_lg")}=$facebookId&")
            append("${encrypt("ie_dw")}=$facebookToken")
        }
        return link.toString()
    }
}