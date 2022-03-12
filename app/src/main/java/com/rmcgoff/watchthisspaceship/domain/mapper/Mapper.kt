package com.rmcgoff.watchthisspaceship.domain.mapper

interface Mapper<F, T> {
    suspend fun map(from: F): T
    suspend fun mapList(fromList: List<F>) : List<T>{
        // Default implementation
        return emptyList()
    }
}