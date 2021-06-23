package com.dimvvmkotlinbinding.ui.dashboard

import android.os.Bundle
import com.dimvvmkotlinbinding.R
import com.dimvvmkotlinbinding.base.app_abstracts.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity()
{
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}