package com.rmcgoff.watchthisspaceship.mockdata

import com.rmcgoff.watchthisspaceship.TEST_COMPANY
import com.rmcgoff.watchthisspaceship.TEST_LAUNCHES_CACHE
import com.rmcgoff.watchthisspaceship.domain.model.Company
import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.domain.SpaceXRepository

/**
 * This mock repo will fail all network calls
 */
class MockSpaceXRepoNoNetworkConnection : SpaceXRepository {
    override suspend fun getLatestLaunchesAscending(): List<LaunchEntity> {
        // throw network exception
       throw Exception()
    }

    override suspend fun getLatestLaunchesDescending(): List<LaunchEntity> {
        // throw network exception
        throw Exception()
    }

    override suspend fun getCachedLaunchesAscending(): List<LaunchEntity> {
        return TEST_LAUNCHES_CACHE
    }

    override suspend fun getCachedLaunchesDescending(): List<LaunchEntity> {
        return TEST_LAUNCHES_CACHE
    }

    override suspend fun getCompanyInfo(): Company {
        return TEST_COMPANY
    }
}