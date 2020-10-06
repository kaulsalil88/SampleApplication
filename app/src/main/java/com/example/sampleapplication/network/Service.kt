package com.example.sampleapplication.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface ProfileService {
    @GET("?randomapi")
    suspend fun getProfile(): ProfileContainer
}


object ProfileNetwork {

    private val retrofit = Retrofit.Builder().
    baseUrl("https://randomuser.me/api/0.4/").addConverterFactory(MoshiConverterFactory.create()).build()
    val profiles = retrofit.create(ProfileService::class.java)
}