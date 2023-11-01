package com.example.appsale19052023.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.appsale19052023.R
import com.example.appsale19052023.data.api.AppResource
import com.example.appsale19052023.presentation.viewmodel.ProductViewModel
import com.example.appsale19052023.util.ToastUtils

class ProductActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        productViewModel.getListProducts().observe(this) {
            when (it) {
                is AppResource.Success -> Log.d("pphat", it.data?.size.toString())
                is AppResource.Error -> ToastUtils.showToast(this, it.error)
            }
        }

        productViewModel.executeGetListProducts()
    }
}