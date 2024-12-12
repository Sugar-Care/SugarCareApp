package com.rayhdf.sugarcareapp.data.api

import com.rayhdf.sugarcareapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    private const val BASE_URL_USER = BuildConfig.BASE_URL_USER
    private const val BASE_URL_PREDICT = BuildConfig.BASE_URL_PREDICT
    private const val BASE_URL_NEWS = "https://newsapi.org/v2/"

    val userApi: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_USER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val predictApi: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_PREDICT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val newsApi: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}