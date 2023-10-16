package com.example.appsale19052023.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.appsale19052023.R
import com.example.appsale19052023.data.api.AppResource
import com.example.appsale19052023.presentation.viewmodel.SignInViewModel
import com.example.appsale19052023.util.ToastUtils
import com.google.android.material.textfield.TextInputEditText

class SignInActivity : AppCompatActivity() {

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var ediTextEmail: TextInputEditText
    private lateinit var ediTextPassword: TextInputEditText
    private lateinit var buttonSignIn: LinearLayout
    private lateinit var tvRegister: TextView
    private lateinit var layoutLoading: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        signInViewModel = ViewModelProvider(this)[SignInViewModel::class.java]

        initView()
        observerData()
        event()

    }

    private fun observerData() {
        signInViewModel.getLoading().observe(this) {
            layoutLoading.isGone = !it
        }

        signInViewModel.getUser().observe(this) {
            when (it) {
                is AppResource.Success -> ToastUtils.showToast(this, "Login success!!!")
                is AppResource.Error -> ToastUtils.showToast(this, it.error)
            }
        }
    }

    private fun event() {
        buttonSignIn.setOnClickListener {
            val email = ediTextEmail.text.toString()
            val password = ediTextPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                ToastUtils.showToast(this, "Input invalid!!!")
                return@setOnClickListener
            }

            signInViewModel.executeSignIn(email, password)
        }
    }

    private fun initView() {
        ediTextEmail = findViewById(R.id.text_edit_email)
        ediTextPassword = findViewById(R.id.text_edit_password)
        buttonSignIn = findViewById(R.id.button_sign_in)
        tvRegister = findViewById(R.id.text_view_register)
        layoutLoading = findViewById(R.id.layout_loading)
    }
}