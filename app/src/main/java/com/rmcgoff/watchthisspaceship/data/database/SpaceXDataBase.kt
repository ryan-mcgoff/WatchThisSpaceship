package com.rmcgoff.watchthisspaceship.data.database

import com.rmcgoff.watchthisspaceship.data.database.dao.LaunchesDao

interface SpaceXDataBase {
    fun launchesDao(): LaunchesDao
}