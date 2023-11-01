package com.example.appsale19052023.data.repository

import com.example.appsale19052023.data.api.RetrofitClient
import com.example.appsale19052023.data.api.dto.AppResponseDTO
import com.example.appsale19052023.data.api.dto.ProductDTO
import retrofit2.Call

object ProductRepository {
    private val apiService = RetrofitClient.getApiService()

    fun requestListProducts(): Call<AppResponseDTO<List<ProductDTO>>> {
        return apiService.getListProducts()
    }
}