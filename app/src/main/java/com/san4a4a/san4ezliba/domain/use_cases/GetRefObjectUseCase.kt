package com.san4a4a.san4ezliba.domain.use_cases

import com.san4a4a.san4ezliba.utils.Cipher.encrypt
import org.json.JSONObject
import java.net.URLDecoder
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class GetRefObjectUseCase @Inject constructor(){
    operator fun invoke(ref: String, facebookKey: String, attributeNameList: List<String>): JSONObject? {
        try {
            val urlForDecode = URLDecoder.decode(ref.split(encrypt(attributeNameList[0]))[1], "utf-8")
            val jsonUrl = JSONObject(urlForDecode)
            val source = JSONObject(jsonUrl[encrypt(attributeNameList[1])].toString())
            val data = source[encrypt(attributeNameList[2])]
            val nonce = source[encrypt(attributeNameList[3])]
            val message = data.toString().decodeHex()
            val keyFacebook = facebookKey.decodeHex()
            val specKey = SecretKeySpec(keyFacebook, encrypt(attributeNameList[4]))
            val secretKeyFacebookFromReferrer = nonce.toString().decodeHex()
            val nonceSpec = IvParameterSpec(secretKeyFacebookFromReferrer)
            val c = Cipher.getInstance(encrypt(attributeNameList[4]))
            c.init(Cipher.DECRYPT_MODE, specKey, nonceSpec)

            return JSONObject(String(c.doFinal(message)))

        } catch (e: Exception) {
            return null
        }
    }

    private fun String.decodeHex(): ByteArray {
        check(length % 2 == 0) { "Wrong length!" }
        return chunked(2).map { it.toInt(16).toByte() }.toByteArray()
    }
}