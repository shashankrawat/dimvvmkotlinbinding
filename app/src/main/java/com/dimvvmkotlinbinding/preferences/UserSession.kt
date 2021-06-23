package com.dimvvmkotlinbinding.preferences

import android.util.Log
import com.dimvvmkotlinbinding.common.helpers.PreferencesHelper
import com.google.gson.Gson
import com.dimvvmkotlinbinding.data.UserDataBean
import javax.inject.Inject

class UserSession @Inject constructor(
    private val mPreference: PreferencesHelper,
    private val mGson: Gson
) {

    companion object {
        private val TAG = UserSession::class.java.name

        const val PREF_IS_SIGNED_IN = "is_signed_in"
        const val PREF_DEVICE_ID = "pref_device_id"
        const val PREF_USER_TOKEN = "user_token"
        const val PREF_USER_DATA = "user_data"
    }


    fun saveIsUserSignedIn(isUserLoggedIn: Boolean) {
        mPreference.savePreferencesBoolean(PREF_IS_SIGNED_IN, isUserLoggedIn)
    }

    val isUserLoggedIn: Boolean
        get() = mPreference.getPreferencesBoolean(PREF_IS_SIGNED_IN)

    fun saveDeviceId(deviceId: String) {
        mPreference.savePreferenceString(PREF_DEVICE_ID, deviceId)
    }

    val savedDeviceId: String?
        get() = mPreference.getPreferenceString(PREF_DEVICE_ID)

    fun saveUserData(userData: UserDataBean?) {
        mPreference.savePreferencesBoolean(PREF_IS_SIGNED_IN, true)
        mPreference.savePreferenceString(PREF_USER_DATA, mGson.toJson(userData))
    }

    val savedUserData: UserDataBean?
        get() {
            var user: UserDataBean? = null
            try {
                user = mGson.fromJson(
                    mPreference.getPreferenceString(PREF_USER_DATA),
                    UserDataBean::class.java
                )
            } catch (ex: Exception) {
                Log.e(TAG, ex.toString())
            }
            return user
        }

    fun saveUserToken(token: String?) {
        mPreference.savePreferenceString(PREF_USER_TOKEN, token)
    }

    val savedUserToken: String?
        get() = mPreference.getPreferenceString(PREF_USER_TOKEN)


}