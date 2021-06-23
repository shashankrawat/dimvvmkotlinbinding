package com.dimvvmkotlinbinding.common.helpers

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesHelper @Inject constructor(@ApplicationContext private val context: Context)
{
    companion object {
        const val PREFERENCE_NAME = "com.pcs.veriskpcs"
    }
    private val defaultValue = 0
    private var mSharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    /**
     * Shared Preference.
     */
    // Save strings in preference
    fun savePreferenceString(key: String?, value: String?) {
        val editor = mSharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // Save boolean values in preference
    fun savePreferencesBoolean(key: String?, value: Boolean) {
        val editor = mSharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    // Save boolean values in preference
    fun savePreferencesLong(key: String?, value: Long) {
        val editor = mSharedPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    // Save int values in preference
    fun savePreferencesInt(key: String?, value: Int) {
        val editor = mSharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    // Get string values from preference
    fun getPreferenceString(key: String?): String? {
        return mSharedPreferences.getString(key, null)
    }

    // Get boolean values from preference
    fun getPreferencesBoolean(key: String?): Boolean {
        return mSharedPreferences.getBoolean(key, false) //false is default value
    }

    // Get Long values from preference
    fun getPreferencesLong(key: String?): Long {
        return mSharedPreferences.getLong(key, defaultValue.toLong()) //false is default value
    }

    // Get int values from preference
    fun getPreferencesInt(key: String?): Int {
        return mSharedPreferences.getInt(key, defaultValue) //false is default value
    }


}