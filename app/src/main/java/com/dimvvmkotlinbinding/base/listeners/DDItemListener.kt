package com.dimvvmkotlinbinding.base.listeners

interface DDItemListener<T> {
    fun onItemClicked(data: T?, position: Int, ddType: Int)
}