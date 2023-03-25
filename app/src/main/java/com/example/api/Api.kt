package com.example.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object Api  {
    var retrofitService:IApi? = null

    fun getInstance():IApi {
        if (retrofitService == null) {
            var levelType:HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
            var logging = HttpLoggingInterceptor()
            logging.setLevel(levelType)
            var client = OkHttpClient.Builder()
            client.readTimeout(120, TimeUnit.SECONDS)
            client.writeTimeout(120, TimeUnit.SECONDS)
            client.addInterceptor(logging)
            var retrofit = Retrofit.Builder().baseUrl("").client(client.build()).addConverterFactory(GsonConverterFactory.create()).build()
            retrofitService = retrofit.create(IApi::class.java)
        }
        return retrofitService!!
    }

}


