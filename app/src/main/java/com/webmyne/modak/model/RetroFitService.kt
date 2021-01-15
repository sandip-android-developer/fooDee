package com.webmyne.modak.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroFitService {
    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(40, TimeUnit.SECONDS)
        .connectTimeout(40, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
//                .retryOnConnectionFailure(false)
        .build()

    fun getService():Api {
        var retrofit = Retrofit.Builder()
            .baseUrl("http://www.gosigmaway.com/api/ca_api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create<Api>(Api::class.java)
    }

}