package com.san4a4a.san4ezliba.data.local

interface Local {
    fun saveLinkToPref(name: String, value: String)
    fun getLinkFromPref(name: String) : String?
}