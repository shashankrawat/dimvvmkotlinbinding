package com.dimvvmkotlinbinding.base.app_abstracts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment : DialogFragment() {
    private var activity: BaseActivity? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity = requireActivity() as BaseActivity?
    }
}