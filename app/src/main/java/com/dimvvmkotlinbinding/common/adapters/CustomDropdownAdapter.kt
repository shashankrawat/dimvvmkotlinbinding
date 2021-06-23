package com.dimvvmkotlinbinding.common.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.dimvvmkotlinbinding.base.listeners.DDItemListener
import com.dimvvmkotlinbinding.R
import com.dimvvmkotlinbinding.databinding.AdapterDropdownItemViewBinding

class CustomDropdownAdapter<T> : RecyclerView.Adapter<CustomDropdownAdapter<T>.ViewHolder>()
{

    private var dataList: List<T> ?= null
    private var listener: DDItemListener<T> ?= null
    private var ddType: Int = 0
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

    fun setTypeNListener(type: Int, listener: DDItemListener<T>){
        ddType = type
        this.listener = listener
    }

    fun addAllData(data: List<T>){
        dataList = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: AdapterDropdownItemViewBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        fun setData(item: T) {
            val ddText = ""
            /*if(ddType == AppConstants.DD_DISTRICT_TYPE){
                DistrictsBean distItem = (DistrictsBean) item;
                ddText = distItem.getDistName();
            }*/
            binding.ddItemTv.text = ddText
        }

        override fun onClick(v: View) {
            listener?.onItemClicked(dataList!![layoutPosition], layoutPosition, ddType)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}