package com.example.pingocean.retrofit

import com.example.pingocean.data.AuthResponse
import com.example.pingocean.data.Profile
import com.example.pingocean.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {
    @GET("/profile")
    suspend fun getProfiles(@Header("Authorization") token: String): Response<Profile>
    @POST("/auth")
    suspend fun getToken(@Body user: User): Response<AuthResponse>
}