package com.san4a4a.san4ezliba.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceManager @Inject constructor(@ApplicationContext private val context: Context) : Local {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("Prefs", Context.MODE_PRIVATE)

    override fun saveLinkToPref(name: String, value: String) {
        prefs.edit().putString(name, value).apply()
    }

    override fun getLinkFromPref(name: String): String? {
        return prefs.getString(name, "")
    }
}