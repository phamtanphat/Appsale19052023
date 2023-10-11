package com.example.appsale19052023.data.api

sealed class AppResource<out T: Any> {
    data class SUCCESS<out T: Any>(val data: T?): AppResource<T>()
    data class ERROR(val error: String): AppResource<Nothing>()
}