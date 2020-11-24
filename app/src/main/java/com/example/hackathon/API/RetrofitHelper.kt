package com.example.hackathon.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper{
    private var retrofit = Retrofit.Builder()
        .baseUrl("http://54.180.119.69")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getUserAPI() : UserAPI{
        return retrofit.create(UserAPI::class.java)
    }

}