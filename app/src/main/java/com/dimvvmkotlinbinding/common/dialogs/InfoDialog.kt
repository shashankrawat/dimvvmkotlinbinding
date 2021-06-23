package com.dimvvmkotlinbinding.common.dialogs

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimvvmkotlinbinding.base.app_abstracts.BaseDialogFragment
import com.dimvvmkotlinbinding.base.listeners.ConfirmationDialogListener
import com.dimvvmkotlinbinding.utils.AppConstants
import com.dimvvmkotlinbinding.R
import com.dimvvmkotlinbinding.databinding.DialogInfoViewBinding

class InfoDialog : BaseDialogFragment(), View.OnClickListener {
    private var titleText: String? = null
    private var msg: String? = null
    private var listener: ConfirmationDialogListener? = null
    private var binding: DialogInfoViewBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            titleText = args.getString(AppConstants.KEY_POPUP_TITLE)
            msg = args.getString(AppConstants.KEY_POPUP_MSG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogInfoViewBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    fun setListener(listener: ConfirmationDialogListener?) {
        this.listener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!TextUtils.isEmpty(titleText)) {
            binding!!.titleText.text = titleText
        }
        if (!TextUtils.isEmpty(msg)) {
            binding!!.titleDesc.text = msg
        }
        binding!!.okBtn.setOnClickListener(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.ok_btn) {
            if (listener != null) {
                listener!!.onOkClicked()
            }
            dismiss()
        }
    }


    fun setArgs(args: Bundle?){
        arguments = args
    }

    companion object {
        const val TAG = "InfoDialog"
    }
}