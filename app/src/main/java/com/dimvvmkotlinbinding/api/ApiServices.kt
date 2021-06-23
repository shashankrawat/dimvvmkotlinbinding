package com.dimvvmkotlinbinding.api

import com.dimvvmkotlinbinding.data.UserDataBean
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
    @FormUrlEncoded
    @POST("login")
    suspend fun getUserLogin(@Field("userId") email: String,
                             @Field("password") pass:String): Response<UserDataBean?>
}