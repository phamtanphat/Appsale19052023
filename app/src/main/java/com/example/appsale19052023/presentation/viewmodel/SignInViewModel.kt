package com.example.appsale19052023.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsale19052023.data.api.AppResource
import com.example.appsale19052023.data.api.dto.AppResponseDTO
import com.example.appsale19052023.data.api.dto.UserDTO
import com.example.appsale19052023.data.model.User
import com.example.appsale19052023.data.repository.AuthenticationRepository
import com.example.appsale19052023.util.UserUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import launchOnMain
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {

    private val loadingLiveData = MutableLiveData<Boolean>()
    private val userLiveData = MutableLiveData<AppResource<User>>()
    fun getLoading(): LiveData<Boolean> = loadingLiveData
    fun getUser(): LiveData<AppResource<User>> = userLiveData

    fun executeSignIn(email: String, password: String) {
        loadingLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            AuthenticationRepository
                .requestSignIn(email, password)
                .enqueue(object : Callback<AppResponseDTO<UserDTO>> {
                    override fun onResponse(
                        call: Call<AppResponseDTO<UserDTO>>,
                        response: Response<AppResponseDTO<UserDTO>>
                    ) {
                        if (response.errorBody() != null) {
                            val errorResponse = response.errorBody()?.string() ?: "{}"
                            val jsonError = JSONObject(errorResponse)
                            userLiveData.value = AppResource.Error(jsonError.optString("message"))
                        } else {
                            val user = UserUtils.parseUserDTO(response.body()?.data)
                            userLiveData.value = AppResource.Success(user)
                        }

                        loadingLiveData.value = false
                    }

                    override fun onFailure(call: Call<AppResponseDTO<UserDTO>>, t: Throwable) {
                        userLiveData.value = AppResource.Error(t.message.toString())
                        loadingLiveData.value = false
                    }
                })
        }
    }
}