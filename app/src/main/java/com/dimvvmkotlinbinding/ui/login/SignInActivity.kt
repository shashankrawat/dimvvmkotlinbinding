package com.dimvvmkotlinbinding.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dimvvmkotlinbinding.R
import com.dimvvmkotlinbinding.api.response_handlers.Status
import com.dimvvmkotlinbinding.base.app_abstracts.BaseActivity
import com.dimvvmkotlinbinding.databinding.ActivitySignInBinding
import com.dimvvmkotlinbinding.ui.dashboard.MainActivity
import com.dimvvmkotlinbinding.ui.login.viewmodel.SignInViewModel
import com.dimvvmkotlinbinding.utils.AppConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignInActivity : BaseActivity()
{

    private var context: Context? = null
    private val model: SignInViewModel by viewModels()
    private lateinit var bindView: ActivitySignInBinding
    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        rootView = bindView.root
        setContentView(rootView)
        initializeItems()
        bindView.viewmodel = model
        subscribeViewModel()
    }

    private fun initializeItems() {
        context = this
        setHyperLinkText()

    }

    private fun setHyperLinkText() {
        bindView.termConditionTv.movementMethod = LinkMovementMethod.getInstance()
        val text =
            "By signing up, you agree to our <font color=\"#000000\"><a href='https://www.google.com'>terms of service</a></font><br/> and <font color=\"#000000\"><a href='https://www.google.com'>privacy policy</a></font>."
        val s = Html.fromHtml(text) as Spannable
        for (u in s.getSpans(0, s.length, URLSpan::class.java)) {
            s.setSpan(object : UnderlineSpan() {
                override fun updateDrawState(tp: TextPaint) {
                    tp.isUnderlineText = false
                }
            }, s.getSpanStart(u), s.getSpanEnd(u), 0)
        }
        bindView.termConditionTv.text = s
    }

    private fun subscribeViewModel() {

        model.observeLogin().observe(this, Observer { resource ->
            if (resource == null) {
                return@Observer
            }
            when (resource.status) {
                Status.LOADING -> showProgressDialog()
                Status.SUCCESS -> {
                    dismissProgressDialog()
                    startActivity(Intent(context, MainActivity::class.java))
                    finishAffinity()
                }
                Status.ERROR -> {
                    dismissProgressDialog()
                    showSnackBar(rootView!!, resource.message.toString())
                }
            }
        })

        model.observeErrorData().observe(this, Observer { errorRes ->
            if (errorRes == null) {
                return@Observer
            }
            when (errorRes.errorOf) {
                AppConstants.ErrorEmail -> {
                    bindView.emailEt.error = errorRes.errorMsg
                    bindView.emailEt.requestFocus()
                }
                AppConstants.ErrorPassword -> {
                    bindView.emailEt.error = null
                    bindView.passwordEt.error = errorRes.errorMsg
                    bindView.passwordEt.requestFocus()
                }
            }
        })
    }


    companion object {
        private const val TAG = "SignInActivity"
    }
}