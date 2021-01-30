package net.tinat.group.eventen.utils

import android.content.Context
import net.tinat.group.eventen.R
import net.tinat.group.eventen.di.getSharedPrefs

class Constants {

    fun getCurrentLanguage(context: Context): String? {
        return getSharedPrefs(context).getString(context.getString(R.string.pref_language), "ar")
    }

    fun setCurrentLanguage(context: Context, lang: String) {
        getSharedPrefs(context).edit().putString(context.getString(R.string.pref_language), lang).apply()
    }

    fun getUid(context: Context): String? {
        return getSharedPrefs(context).getString(context.getString(R.string.pref_uid), "")
    }

    fun setUid(context: Context, uid: String) {
        getSharedPrefs(context).edit().putString(context.getString(R.string.pref_uid), uid).apply()
    }
}

