package com.dimvvmkotlinbinding.base.app_abstracts

import android.text.TextUtils
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimvvmkotlinbinding.data.ErrorBean

abstract class BaseViewModel() : ViewModel() {
    private val errorFields = ErrorBean("", 0)
    private val errorData : MutableLiveData<ErrorBean> = MutableLiveData()


    open fun setErrorData(msg: String, errorType: Int) {
        errorFields.errorMsg = msg
        errorFields.errorOf = errorType
        errorData.value = errorFields
    }

    open fun observeErrorData(): MutableLiveData<ErrorBean> {
        return errorData
    }


    fun getVisibility(textValue: String?): Int {
        return if (!TextUtils.isEmpty(textValue)) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun getVisibility(isEnabled: Boolean): Int {
        return if (isEnabled) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun getVisibilityInvisible(isEnabled: Boolean): Int {
        return if (isEnabled) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }
}