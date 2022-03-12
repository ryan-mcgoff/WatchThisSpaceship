package com.rmcgoff.watchthisspaceship.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity

@Dao
interface LaunchesDao {
    @Query("SELECT * FROM launch_table")
    suspend fun getAll(): List<LaunchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(launches: List<LaunchEntity>)
}