package com.example.appsale19052023.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsale19052023.data.api.AppResource
import com.example.appsale19052023.data.api.dto.AppResponseDTO
import com.example.appsale19052023.data.api.dto.ProductDTO
import com.example.appsale19052023.data.model.Product
import com.example.appsale19052023.data.repository.ProductRepository
import com.example.appsale19052023.util.ProductUtils
import com.example.appsale19052023.util.UserUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {

    private val loadingLiveData = MutableLiveData<Boolean>()
    private val listProductsLiveData = MutableLiveData<AppResource<List<Product>>>()
    fun getLoading(): LiveData<Boolean> = loadingLiveData
    fun getListProducts(): LiveData<AppResource<List<Product>>> = listProductsLiveData

    fun executeGetListProducts() {
        loadingLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            ProductRepository
                .requestListProducts()
                .enqueue(object : Callback<AppResponseDTO<List<ProductDTO>>> {
                    override fun onResponse(
                        call: Call<AppResponseDTO<List<ProductDTO>>>,
                        response: Response<AppResponseDTO<List<ProductDTO>>>
                    ) {
                        if (response.errorBody() != null) {
                            val errorResponse = response.errorBody()?.string() ?: "{}"
                            val jsonError = JSONObject(errorResponse)
                            listProductsLiveData.value =
                                AppResource.Error(jsonError.optString("message"))
                        } else {
                            val listProducts = response.body()?.data?.map { ProductUtils.parseProductDTO(it) }
                            listProductsLiveData.value = AppResource.Success(listProducts)
                        }

                        loadingLiveData.value = false
                    }

                    override fun onFailure(
                        call: Call<AppResponseDTO<List<ProductDTO>>>,
                        t: Throwable
                    ) {
                        Log.d("pphat", t.message.toString())
                    }
                })
        }
    }
}
