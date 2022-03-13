package com.rmcgoff.watchthisspaceship.domain

import com.rmcgoff.watchthisspaceship.cache.database.SpaceXDataBase
import com.rmcgoff.watchthisspaceship.cache.entity.CompanyEntity
import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.domain.mapper.CompanyEntityMapper
import com.rmcgoff.watchthisspaceship.domain.mapper.LaunchEntityMapper
import com.rmcgoff.watchthisspaceship.network.datasource.SpaceXNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Responsible for interfacing between the network and the database layers
 */
class DefaultSpaceXRepository @Inject constructor(
    private val networkDataSource: SpaceXNetworkDataSource,
    private val database: SpaceXDataBase,
    private val launchEntityMapper: LaunchEntityMapper,
    private val companyEntityMapper: CompanyEntityMapper
) : SpaceXRepository {

    private suspend fun getLatestLaunchesAndCache() {
        // Get latest data from Network
        val networkResponse = networkDataSource.getAllLaunches()
        val mappedResponse = launchEntityMapper.mapList(networkResponse)
        // Clear previous cache
        database.launchesDao().deleteAll()
        // Update cache with new data
        database.launchesDao().insertAll(mappedResponse)
    }

    /**
     * Warning, will throw network error if no available connection. Call in try / catch
     */
    override suspend fun getLatestLaunchesAscending(): List<LaunchEntity> {
        return withContext(Dispatchers.IO) {
            getLatestLaunchesAndCache()
            // return cache response
            getCachedLaunchesAscending()
        }
    }

    /**
     * Warning, will throw network error if no available connection. Call in try / catch
     */
    override suspend fun getLatestLaunchesDescending(): List<LaunchEntity> {
        return withContext(Dispatchers.IO) {
            getLatestLaunchesAndCache()
            // return cache response
            getCachedLaunchesDescending()
        }
    }

    override suspend fun getCachedLaunchesAscending(): List<LaunchEntity> {
        return withContext(Dispatchers.IO) {
            // return cache response
            database.launchesDao().getAll()
        }
    }

    override suspend fun getCachedLaunchesDescending(): List<LaunchEntity> {
        return withContext(Dispatchers.IO) {
            // return cache response
            database.launchesDao().getAllDescendingDate()
        }
    }

    /**
     * Warning, will throw network error if no available connection. Call in try / catch
     */
    override suspend fun getCompanyInfo(): CompanyEntity {
        return withContext(Dispatchers.IO) {
            companyEntityMapper.map(networkDataSource.getCompanyInfo())
        }
    }
}