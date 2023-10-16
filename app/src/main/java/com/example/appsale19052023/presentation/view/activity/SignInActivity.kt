package com.example.appsale19052023.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.appsale19052023.R
import com.example.appsale19052023.presentation.viewmodel.SignInViewModel

class SignInActivity : AppCompatActivity() {

    private lateinit var signInViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        signInViewModel = ViewModelProvider(this)[SignInViewModel::class.java]

        signInViewModel.executeSignIn("demo2@gmail.com", "12345678")
    }
}