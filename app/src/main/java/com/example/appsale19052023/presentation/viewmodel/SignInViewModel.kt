package com.example.appsale19052023.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsale19052023.data.api.dto.AppResponseDTO
import com.example.appsale19052023.data.api.dto.UserDTO
import com.example.appsale19052023.data.repository.AuthenticationRepository
import com.example.appsale19052023.util.UserUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import launchOnMain

class SignInViewModel: ViewModel() {

    private val loadingLiveData = MutableLiveData<Boolean>()

    fun executeSignIn(email: String, password: String) {
        loadingLiveData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val appResourceUserDTO: AppResponseDTO<UserDTO> = async {
                    AuthenticationRepository.requestSignIn(email, password)
                }.await()

                val user = UserUtils.parseUserDTO(appResourceUserDTO.data)
                Log.d("pphat", user.toString())
            } catch (e: Exception) {
                Log.d("pphat", e.message.toString())
            } finally {
                launchOnMain { loadingLiveData.value = false }
            }
        }
    }
}