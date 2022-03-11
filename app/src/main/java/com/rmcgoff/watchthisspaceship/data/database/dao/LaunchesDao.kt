package com.rmcgoff.watchthisspaceship.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rmcgoff.watchthisspaceship.data.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.network.data.launch.Launch

@Dao
interface LaunchesDao {
    @Query("SELECT * FROM launch_table")
    suspend fun getAll(): List<LaunchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(launches: List<LaunchEntity>)
}