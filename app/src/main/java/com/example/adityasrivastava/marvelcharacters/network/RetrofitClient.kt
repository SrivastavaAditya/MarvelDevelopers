package com.example.adityasrivastava.marvelcharacters.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofitInterface: RetrofitInterface
    private const val baseUrl = "https://gateway.marvel.com//v1/public/"

    init {
        retrofitInterface = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RetrofitInterface::class.java)
    }
}