package com.dimvvmkotlinbinding.base.listeners

interface AuthenticationListener {
    fun onTokenReceived(auth_token: String?)
}