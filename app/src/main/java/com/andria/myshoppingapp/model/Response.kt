package com.andria.myshoppingapp.model

sealed class Response<out T> {
    object Loading : Response<Nothing>()
    data class Success<T>(val data: T) : Response<T>()
    data class Error(val exception: Throwable) : Response<Nothing>()
}