package com.rmcgoff.watchthisspaceship.domain

import com.rmcgoff.watchthisspaceship.cache.entity.CompanyEntity
import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity

interface SpaceXRepository {
    suspend fun getLatestLaunchesAscending(): List<LaunchEntity>
    suspend fun getLatestLaunchesDescending(): List<LaunchEntity>
    suspend fun getCachedLaunchesAscending(): List<LaunchEntity>
    suspend fun getCachedLaunchesDescending(): List<LaunchEntity>
    suspend fun getCompanyInfo(): CompanyEntity
}