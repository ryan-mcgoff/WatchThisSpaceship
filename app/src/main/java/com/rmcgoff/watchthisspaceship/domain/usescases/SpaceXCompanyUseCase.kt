package com.rmcgoff.watchthisspaceship.domain.usescases

import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.domain.DataResult
import com.rmcgoff.watchthisspaceship.domain.SpaceXRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class DefaultSpaceXCompanyUseCase @Inject constructor(
    private val repository: SpaceXRepository
) : SpaceXCompanyUseCase {
    override fun doWork(): Flow<DataResult<String>> = flow {
        try {
            emit(DataResult.Loading())
            delay(2000)
            val response = repository.getCompanyInfo()
            // Transform to company summary
            val companySummary = "${response.companyName} was founded by ${response.founderName} in ${response.year}. " +
                "It has now ${response.employees} employees, ${response.launchSites}" +
                " launch sites, and is valued at USD ${response.valuation}"
            emit(DataResult.Success(companySummary))
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

interface SpaceXCompanyUseCase {
    fun doWork(): Flow<DataResult<String>>
}