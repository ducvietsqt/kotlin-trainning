package com.example.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface IApi {
    @POST("/login")
    suspend fun login(
        @Field("usename") username: String,
        @Field("password") password: String
    ):Response<ResponseBody>

}