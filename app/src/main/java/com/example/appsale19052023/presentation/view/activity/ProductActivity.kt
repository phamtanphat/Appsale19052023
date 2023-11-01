package com.example.appsale19052023.presentation.view.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.appsale19052023.R
import com.example.appsale19052023.data.api.AppResource
import com.example.appsale19052023.presentation.view.adapter.ProductAdapter
import com.example.appsale19052023.presentation.viewmodel.ProductViewModel
import com.example.appsale19052023.util.ToastUtils

class ProductActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var layoutLoading: LinearLayout
    private var toolBar : Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        initView()
        observerData()
        event()
    }

    private fun event() {
        productViewModel.executeGetListProducts()
    }

    private fun observerData() {
        productViewModel.getLoading().observe(this) {
            layoutLoading.isGone = !it
        }

        productViewModel.getListProducts().observe(this) {
            when (it) {
                is AppResource.Success -> productAdapter.updateListProduct(it.data)
                is AppResource.Error -> ToastUtils.showToast(this, it.error)
            }
        }
    }

    private fun initView() {
        productRecyclerView = findViewById(R.id.recycler_view_product)
        layoutLoading = findViewById(R.id.layout_loading)
        toolBar = findViewById(R.id.toolbar_home)
        productAdapter = ProductAdapter(context = this)
        productRecyclerView.adapter = productAdapter
        productRecyclerView.setHasFixedSize(true)

        setSupportActionBar(toolBar)
        supportActionBar?.title = "Product"
    }
}