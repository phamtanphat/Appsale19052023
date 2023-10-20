package com.example.appsale19052023.data.repository

import com.example.appsale19052023.data.api.RetrofitClient
import com.example.appsale19052023.data.api.dto.AppResponseDTO
import com.example.appsale19052023.data.api.dto.UserDTO
import retrofit2.Call

object AuthenticationRepository {
    private val apiService = RetrofitClient.getApiService()

    fun requestSignIn(email: String, password: String): Call<AppResponseDTO<UserDTO>> {
        val map = HashMap<String, Any>()
        map["email"] = email
        map["password"] = password
        return apiService.signIn(map)
    }

    fun requestSignUp(
        email: String,
        password: String,
        name: String,
        phone: String,
        address: String
    ): Call<AppResponseDTO<UserDTO>> {
        val map = HashMap<String, Any>()
        map["email"] = email
        map["password"] = password
        map["name"] = name
        map["phone"] = phone
        map["address"] = address
        return apiService.signUp(map)
    }
}