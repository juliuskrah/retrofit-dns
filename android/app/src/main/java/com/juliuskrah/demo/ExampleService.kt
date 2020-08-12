package com.juliuskrah.demo

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ExampleService {
    @GET("/")
    fun name(): Call<Example>

    companion object {
        fun create(): ExampleService {
            val retrofit: Retrofit = Retrofit.Builder()
                .client(
                    OkHttpClient.Builder()
                        .dns(ExampleDns())
                        .build()
                )
                .baseUrl("http://aparel.jaesoft.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ExampleService::class.java)
        }
    }
}