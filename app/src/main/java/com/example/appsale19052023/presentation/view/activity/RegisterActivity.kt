package com.example.appsale19052023.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.appsale19052023.R
import com.example.appsale19052023.data.api.AppResource
import com.example.appsale19052023.presentation.viewmodel.RegisterViewModel
import com.example.appsale19052023.presentation.viewmodel.SignInViewModel
import com.example.appsale19052023.util.ToastUtils

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        registerViewModel.getUser().observe(this) {
            when(it) {
                is AppResource.Success -> ToastUtils.showToast(this, "Register success")
                is AppResource.Error -> ToastUtils.showToast(this, it.error)
            }
        }

        registerViewModel.executeSignUp(
            email = "demo2010@gmail.com",
            password = "12345678",
            name = "test",
            phone = "0123212312",
            address = "District 10"
        )
    }
}