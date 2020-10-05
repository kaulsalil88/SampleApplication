package com.example.sampleapplication.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

////https://randomuser.me/api/0.4/?randomapi
//interface DevbyteService {
//    @GET("devbytes")
//    suspend fun getPlaylist(): NetworkVideoContainer
//}
//
///**
// * Main entry point for network access. Call like `DevByteNetwork.devbytes.getPlaylist()`
// */
//object DevByteNetwork {
//
//    // Configure retrofit to parse JSON and use coroutines
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
//        .addConverterFactory(MoshiConverterFactory.create())
//        .build()
//
//    val devbytes = retrofit.create(DevbyteService::class.java)
//}

interface ProfileService {
    @GET("?randomapi")
    suspend fun getProfile(): ProfileContainer
}


object ProfileNetwork {

    private val retrofit = Retrofit.Builder().
    baseUrl("https://randomuser.me/api/0.4/").addConverterFactory(MoshiConverterFactory.create()).build()
    val profiles = retrofit.create(ProfileService::class.java)
}