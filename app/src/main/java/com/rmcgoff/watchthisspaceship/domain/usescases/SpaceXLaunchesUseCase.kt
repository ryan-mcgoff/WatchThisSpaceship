package com.rmcgoff.watchthisspaceship.domain.usescases

import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.domain.DataResult
import com.rmcgoff.watchthisspaceship.domain.SpaceXRepository
import com.rmcgoff.watchthisspaceship.domain.mapper.LaunchMapper
import com.rmcgoff.watchthisspaceship.domain.mapper.Mapper
import com.rmcgoff.watchthisspaceship.domain.model.Launch
import com.rmcgoff.watchthisspaceship.homeui.dialog.Filter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class DefaultSpaceXLaunchesUseCase @Inject constructor(
    private val repository: SpaceXRepository,
    private val launchMapper: Mapper<LaunchEntity, Launch>
) : SpaceXLaunchesUseCase {
    override fun doWork(filter: Filter): Flow<DataResult<List<Launch>>> = flow {
        try {
            emit(DataResult.Loading<List<Launch>>())
            // For the purposes of demoing the loading states, I've delayed network call by 1 second
            delay(1000)
            val networkResponse = when (filter) {
                Filter.ASCENDING -> repository.getLatestLaunchesAscending()
                Filter.DESCENDING -> repository.getLatestLaunchesDescending()
            }
            emit(DataResult.Success(launchMapper.mapList(networkResponse)))
        } catch (exception: Exception) {
            // If network call didn't work, try cache
            val cachedResults = when (filter) {
                Filter.ASCENDING -> repository.getCachedLaunchesAscending()
                Filter.DESCENDING -> repository.getCachedLaunchesDescending()
            }
            if (cachedResults.isEmpty()) {
                emit(
                    DataResult.Error<List<Launch>>(
                        errorMessage = when (exception) {
                            is HttpException -> "Network Error, please check your connection"
                            else -> "Unknown Error"
                        }
                    )
                )
            } else {
                emit(DataResult.Success(launchMapper.mapList(cachedResults)))
            }
            exception.printStackTrace()
        }
    }
}

interface SpaceXLaunchesUseCase {
    fun doWork(filter: Filter): Flow<DataResult<List<Launch>>>
}