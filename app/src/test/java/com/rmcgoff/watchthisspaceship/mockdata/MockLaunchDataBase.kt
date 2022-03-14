package com.rmcgoff.watchthisspaceship.repositories

import com.rmcgoff.watchthisspaceship.cache.database.SpaceXDataBase
import com.rmcgoff.watchthisspaceship.cache.database.dao.LaunchesDao
import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity

class MockLaunchDataBase: SpaceXDataBase {
    // fake for launch table in local db
    val launches = mutableListOf<LaunchEntity>()
    override fun launchesDao(): LaunchesDao {
        return MockDao(launches)
    }
}


class MockDao(private val mockDataBase: MutableList<LaunchEntity>) : LaunchesDao {
    override suspend fun getAll(): List<LaunchEntity> {
        return mockDataBase
    }

    override suspend fun getAllDescendingDate(): List<LaunchEntity> {
        return mockDataBase.sortedByDescending { it.launchDateUnix }
    }

    override suspend fun insertAll(launches: List<LaunchEntity>) {
        mockDataBase.addAll(launches)
    }

    override suspend fun deleteAll() {
        mockDataBase.clear()
    }
}