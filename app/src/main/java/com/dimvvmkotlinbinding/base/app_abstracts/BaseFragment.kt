package com.dimvvmkotlinbinding.base.app_abstracts

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.dimvvmkotlinbinding.common.dropdowns.AppCustomDropdownMenu
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {
    private var activity: BaseActivity? = null
    private lateinit var mContext: WeakReference<Context>

    @Inject
    lateinit var popupWindow : AppCustomDropdownMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = WeakReference(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity = requireActivity() as BaseActivity?
    }

    fun hideKeyBoard() {
        activity?.hideKeyBoard()
    }

    fun showToast(msg: String?) {
        activity?.showToast(msg)
    }

    fun showSnackBar(msg: String?) {
        Snackbar.make(requireView(), msg!!, Snackbar.LENGTH_SHORT).show()
    }

    fun showProgressDialog() {
        activity?.showProgressDialog()
    }

    fun dismissProgressDialog() {
        activity?.dismissProgressDialog()
    }

    fun setFragment(containerID: Int, fragment: Fragment?, tag: String?, addToStack: Boolean) {
        activity?.setFragment(containerID, fragment, tag, addToStack)
    }

    fun onBackPressed() {
        activity?.onBackPressed()
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    fun checkPermissions(permissions: Array<String?>?): Boolean {
        return activity?.checkPermissions(permissions!!) == true
    }

    fun showDropDown(
        anchor: View,
        ddViewField: ObservableField<String?>,
        ddData: List<String>,
        isWidthFull: Boolean
    ) {
        popupWindow.setDataNObservableField(ddData, ddViewField)
        popupWindow.height = WindowManager.LayoutParams.WRAP_CONTENT
        if (isWidthFull) {
            popupWindow.width = anchor.width
        } else {
            popupWindow.width = anchor.width * 2
        }
        popupWindow.isFocusable = true
        popupWindow.isOutsideTouchable = true
        popupWindow.showAsDropDown(anchor)
    }
}