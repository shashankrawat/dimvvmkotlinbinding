package com.dimvvmkotlinbinding.ui.login.viewmodel

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.dimvvmkotlinbinding.api.response_handlers.Response
import com.dimvvmkotlinbinding.base.app_abstracts.BaseViewModel
import com.dimvvmkotlinbinding.data.LoginBean
import com.dimvvmkotlinbinding.data.UserDataBean
import com.dimvvmkotlinbinding.preferences.UserSession
import com.dimvvmkotlinbinding.ui.login.repo.LoginRepository
import com.dimvvmkotlinbinding.utils.AppConstants
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val loginRepository: LoginRepository,
                                          private val userSession: UserSession
) : BaseViewModel()
{
    var loginFields = LoginBean()
    private val loginLD: MutableLiveData<Response<UserDataBean?>> = MutableLiveData()

    fun observeLogin(): LiveData<Response<UserDataBean?>> {
        return loginLD
    }



    fun isEmailIdValid(email: String?): Boolean {
        return if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            setErrorData("", 0)
            true
        } else {
            setErrorData("Please enter a valid Email.", AppConstants.ErrorEmail)
            false
        }
    }

    fun isPasswordValid(pwd: String?): Boolean {
        return if (!TextUtils.isEmpty(pwd) && pwd!!.length >= 6) {
            setErrorData("", 0)
            true
        } else {
            setErrorData("Password must be of atleast 6 letter", AppConstants.ErrorPassword)
            false
        }
    }


    fun onSignInClick() {
        if (isEmailIdValid(loginFields.email) && isPasswordValid(loginFields.password)) {
            loginLD.value = Response.loading(null)
            viewModelScope.launch {
                loginLD.value = loginRepository.getUserLoginPcs(loginFields)
            }
        }
    }
}