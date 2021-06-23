package com.dimvvmkotlinbinding.common.dropdowns

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.dimvvmkotlinbinding.common.adapters.DropdownAdapter
import com.dimvvmkotlinbinding.base.listeners.OnItemClickedListener
import com.dimvvmkotlinbinding.R
import com.dimvvmkotlinbinding.databinding.DropdownContentViewBinding
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppCustomDropdownMenu @Inject constructor(
    @ApplicationContext private val context: Context,
    private val adapter: DropdownAdapter
) : PopupWindow()
{
    private lateinit var viewBinding: DropdownContentViewBinding
    private lateinit var  ddViewField: ObservableField<String?>
    private lateinit var ddData: List<String>

    fun setDataNObservableField(data: List<String> , viewField: ObservableField<String?>){
        ddData = data
        ddViewField = viewField
    }
    private fun setUpView() {
        viewBinding = DropdownContentViewBinding.inflate(LayoutInflater.from(context))
        viewBinding.dropdownRv.setHasFixedSize(true)
        adapter.setListener(object : OnItemClickedListener<String> {
            override fun onItemClicked(v: View, data: String?, position: Int) {
                dismiss()
                ddViewField.set(data)
            }

            override fun onItemLongClicked(v: View, data: String?, position: Int): Boolean {
                return false
            }

        })
        adapter.addAllData(ddData)
        viewBinding.dropdownRv.adapter = adapter
        contentView = viewBinding.root
    }

    init {
        setUpView()
    }
}