package com.example.appsale19052023.data.api

import com.example.appsale19052023.data.api.dto.AppResponseDTO
import com.example.appsale19052023.data.api.dto.UserDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/sign-in")
    fun signIn(@Body map: HashMap<String, Any>): Call<AppResponseDTO<UserDTO>>
}