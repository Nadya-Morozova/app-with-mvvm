package com.example.applicationwithmvvm.network

import android.content.Context
import androidx.preference.PreferenceManager

class SharedPreferencesRepository(context: Context) {

    companion object {
        const val KEY_INFO = "info"
    }

    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    var info: String?
        get() = pref.getString(KEY_INFO, "No info about words")
        set(value) {
            pref.edit().apply {
                putString(KEY_INFO, value)
                apply()
            }
        }

}