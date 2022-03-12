package com.rmcgoff.watchthisspaceship.cache.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity

@Database(entities = [LaunchEntity::class], version = 1)
abstract class DefaultSpaceXDataBase : RoomDatabase(), SpaceXDataBase