package com.rmcgoff.watchthisspaceship.data.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.rmcgoff.watchthisspaceship.data.entity.LaunchEntity

@Database(entities = [LaunchEntity::class], version = 1)
abstract class DefaultSpaceXDataBase : RoomDatabase(), SpaceXDataBase