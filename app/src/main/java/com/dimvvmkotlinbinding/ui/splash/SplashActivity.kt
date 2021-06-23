package com.dimvvmkotlinbinding.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.dimvvmkotlinbinding.R
import com.dimvvmkotlinbinding.base.app_abstracts.BaseActivity
import com.dimvvmkotlinbinding.ui.login.SignInActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity()
{
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        context = this

        lifecycleScope.launch {
            delay(3000)
            navigateToLoginScreen()
        }

    }

    private fun navigateToLoginScreen() {
        startActivity(Intent(context, SignInActivity::class.java))
        finish()
    }

}