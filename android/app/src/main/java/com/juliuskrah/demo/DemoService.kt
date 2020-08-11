package com.juliuskrah.demo

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET




interface DemoService {
    @GET("/")
    fun name(): Call<Example>

    companion object {
        fun create(): DemoService {
            val retrofit: Retrofit = Retrofit.Builder()
                .client(OkHttpClient.Builder()
                    .dns(CustomDns())
                    .build())
                .baseUrl("http://theshop.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(DemoService::class.java)
        }
    }
}