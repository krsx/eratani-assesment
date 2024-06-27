package com.project.eratani.core.data.source.remote.network

import com.project.eratani.core.data.source.remote.response.UserResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("users")
    suspend fun registerUser(
        @Header("Authorization") token: String,

        @Field("name") name: String,
        @Field("email") email: String,
        @Field("gender") gender: String,
        @Field("status") status: String,
    ): UserResponse

    @GET("users")
    suspend fun getUsers(
        @Header("Authorization") token: String,
    ): List<UserResponse>
}