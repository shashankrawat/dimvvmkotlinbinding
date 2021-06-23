package com.dimvvmkotlinbinding.ui.dashboard.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimvvmkotlinbinding.data.UserDataBean
import com.dimvvmkotlinbinding.databinding.ItemLayoutBinding

class DashboardAdapter : RecyclerView.Adapter<DashboardAdapter.DataViewHolder>() {

    var users = ArrayList<UserDataBean>()

    private lateinit var itemLayoutBinding: ItemLayoutBinding


    class DataViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun bind(user: UserDataBean) {
            binding.userName.text = user.userName
            binding.userEmail.text = user.userPhone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        itemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return DataViewHolder(itemLayoutBinding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    fun addUsers(users: ArrayList<UserDataBean>) {
        this.users.clear()
        this.users = users
    }
}
