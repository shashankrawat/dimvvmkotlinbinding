package com.dimvvmkotlinbinding.api.managers

import com.dimvvmkotlinbinding.api.ApiServices
import com.dimvvmkotlinbinding.data.UserDataBean
import retrofit2.Response
import javax.inject.Inject

class LoginApiManager @Inject constructor(private val apiService: ApiServices) {

    suspend fun getUserLogin(user: String, pass: String): Response<UserDataBean?> {
        return apiService.getUserLogin(user, pass)
    }
}