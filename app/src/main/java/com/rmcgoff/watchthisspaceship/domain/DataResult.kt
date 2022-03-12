package com.rmcgoff.watchthisspaceship.domain

sealed class DataResult<T> {
    class Loading<T> : DataResult<T>()
    data class Error<T>(val errorMessage: String) : DataResult<T>()
    data class Success<T>(val data: T) : DataResult<T>()
}