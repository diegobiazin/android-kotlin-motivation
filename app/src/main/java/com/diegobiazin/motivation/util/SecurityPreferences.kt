package com.diegobiazin.motivation.util

import android.content.Context

class SecurityPreferences(context: Context) {

    private val mSharedPreferences = context.getSharedPreferences("motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getStoreString(key: String): String {
        return mSharedPreferences.getString(key, "")
    }
}