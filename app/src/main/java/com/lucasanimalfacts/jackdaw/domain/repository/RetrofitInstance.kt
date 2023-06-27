package com.lucasanimalfacts.jackdaw.domain.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    val api: SubsonicApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://lucasanimalfacts.com:4533/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SubsonicApi::class.java)
    }
}