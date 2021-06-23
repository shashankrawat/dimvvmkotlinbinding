package com.dimvvmkotlinbinding.common.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.dimvvmkotlinbinding.base.listeners.OnItemClickedListener
import com.dimvvmkotlinbinding.R
import com.dimvvmkotlinbinding.base.listeners.DDItemListener
import com.dimvvmkotlinbinding.databinding.AdapterDropdownItemViewBinding

class DropdownAdapter : RecyclerView.Adapter<DropdownAdapter.ViewHolder>()
{
    private var dataList: List<String> ?= null
    private var listener: OnItemClickedListener<String>?= null
    private lateinit var itemViewBinding: AdapterDropdownItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemViewBinding = AdapterDropdownItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataList!![position])
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    fun setListener(listener: OnItemClickedListener<String>){
        this.listener = listener
    }

    fun addAllData(data: List<String>){
        dataList = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: AdapterDropdownItemViewBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        fun setData(item: String?) {
            binding.ddItemTv.text = item
        }

        override fun onClick(v: View) {
            listener?.onItemClicked(v, dataList?.get(layoutPosition), layoutPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}