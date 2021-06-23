package com.dimvvmkotlinbinding.ui.login.repo

import com.dimvvmkotlinbinding.api.managers.LoginApiManager
import com.dimvvmkotlinbinding.api.response_handlers.Response
import com.dimvvmkotlinbinding.common.helpers.NetworkHelper
import com.dimvvmkotlinbinding.data.LoginBean
import com.dimvvmkotlinbinding.data.UserDataBean
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val loginApiManager: LoginApiManager,
    private val networkHelper: NetworkHelper
) {

    suspend fun getUserLoginPcs(loginData : LoginBean): Response<UserDataBean?> {
        return withContext(IO) {
            if (!networkHelper.isNetworkConnected()) {
                Response.error("Offline", null, 0, null)
            }
            val response = loginApiManager.getUserLogin(loginData.email,loginData.password)
            return@withContext when {
                response.body() != null && response.isSuccessful ->
                    Response.success(response.body())

                else -> {
                    Response.error(response.message(),null, 0, null)
                }

            }
        }
    }

}
