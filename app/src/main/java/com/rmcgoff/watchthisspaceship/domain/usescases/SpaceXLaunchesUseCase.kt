package com.rmcgoff.watchthisspaceship.domain.usescases

import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.domain.DataResult
import com.rmcgoff.watchthisspaceship.domain.SpaceXRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class DefaultSpaceXLaunchesUseCase @Inject constructor(
    private val repository: SpaceXRepository
) : SpaceXLaunchesUseCase {
    override fun doWork(): Flow<DataResult<List<LaunchEntity>>> = flow {
        try {
            emit(DataResult.Loading())
            kotlinx.coroutines.delay(2000)
            val response = repository.getCachedLaunchesAscending()
            emit(DataResult.Success(response))
        } catch (exception: Exception) {
            emit(
                DataResult.Error(
                    when (exception) {
                        is HttpException -> "Network Error, please check your connection"
                        else -> "Unknown Error"
                    }
                )
            )
            exception.printStackTrace()
        }
    }
}

interface SpaceXLaunchesUseCase {
    fun doWork(): Flow<DataResult<List<LaunchEntity>>>
}