package com.rmcgoff.watchthisspaceship.cache.database

import com.rmcgoff.watchthisspaceship.cache.database.dao.LaunchesDao

interface SpaceXDataBase {
    fun launchesDao(): LaunchesDao
}