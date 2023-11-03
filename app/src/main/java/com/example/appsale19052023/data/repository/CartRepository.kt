package com.example.appsale19052023.data.repository

import com.example.appsale19052023.data.api.RetrofitClient
import com.example.appsale19052023.data.api.dto.AppResponseDTO
import com.example.appsale19052023.data.api.dto.CartDTO
import retrofit2.Call

object CartRepository {
    private val apiService = RetrofitClient.getApiService()

    fun requestCart(): Call<AppResponseDTO<CartDTO>> {
        return apiService.getCart()
    }
}