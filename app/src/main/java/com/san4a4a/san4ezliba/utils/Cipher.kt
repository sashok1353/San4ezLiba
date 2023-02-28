package com.san4a4a.san4ezliba.utils

object Cipher {
    private val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

    var key: Int = 30

    fun encrypt(cipherText: String): String {
        var plainText = ""
        for (i in cipherText.indices) {
            val currentChar = cipherText[i]
            if (currentChar.isLetterOrDigit()) {
                val currentIndex = alphabet.indexOf(currentChar)
                var newIndex = (currentIndex - key) % alphabet.length
                if (newIndex < 0) {
                    newIndex += alphabet.length
                }
                val newChar = alphabet[newIndex]
                plainText += newChar
            } else {
                plainText += currentChar
            }
        }
        return plainText
    }
}