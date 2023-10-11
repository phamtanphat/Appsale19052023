package com.example.appsale19052023.data.repository

import com.example.appsale19052023.data.api.RetrofitClient
import com.example.appsale19052023.data.api.dto.AppResponseDTO
import com.example.appsale19052023.data.api.dto.UserDTO

object AuthenticationRepository {
    private val apiService = RetrofitClient.getApiService()

    suspend fun requestSignIn(email: String, password: String): AppResponseDTO<UserDTO> {
        val map = HashMap<String, Any>()
        map["email"] = email
        map["password"] = password
        return apiService.signIn(map)
    }
}