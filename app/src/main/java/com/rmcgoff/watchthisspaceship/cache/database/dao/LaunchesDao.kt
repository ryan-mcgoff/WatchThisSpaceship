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

    @Query("SELECT * FROM launch_table ORDER BY launchDateUnix DESC")
    suspend fun getAllDescendingDate(): List<LaunchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(launches: List<LaunchEntity>)

    @Query("DELETE FROM launch_table")
    suspend fun deleteAll()
}