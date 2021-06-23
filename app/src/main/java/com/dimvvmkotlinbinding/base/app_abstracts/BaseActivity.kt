package com.dimvvmkotlinbinding.base.app_abstracts

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dimvvmkotlinbinding.R
import com.dimvvmkotlinbinding.common.dialogs.ProgressBarDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity()
{
    @Inject
    lateinit var progressBar: ProgressBarDialog


    fun hideKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showSnackBar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    fun showProgressDialog() {
        progressBar.show(supportFragmentManager, ProgressBarDialog.TAG)
    }

    fun dismissProgressDialog() {
        progressBar.dismiss()
    }

    fun setFragment(containerId: Int, fragment: Fragment?, tag: String?, addToStack: Boolean) {
        if (fragment == null) {
            return
        }
        try {
            val fragTransaction = supportFragmentManager.beginTransaction()
            fragTransaction.add(containerId, fragment, tag)
            if (addToStack) fragTransaction.addToBackStack(tag)
            fragTransaction.commit()
        } catch (e: Exception) {
            Log.e(tag, "" + e.toString())
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    fun checkPermissions(permissions: Array<String?>): Boolean {
        for (permission in permissions) {
            if (checkSelfPermission(permission!!) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}