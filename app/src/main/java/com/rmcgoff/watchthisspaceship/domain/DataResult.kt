package com.rmcgoff.watchthisspaceship.domain

/**
 * DataResult class, wrap response in this class
 */
sealed class DataResult<T> {
    class Loading<T> : DataResult<T>()
    data class Error<T>(val errorMessage: String) : DataResult<T>()
    data class Success<T>(val data: T) : DataResult<T>()
}