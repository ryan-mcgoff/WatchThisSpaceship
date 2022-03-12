package com.rmcgoff.watchthisspaceship.cache

import android.content.Context
import androidx.room.Room
import com.rmcgoff.watchthisspaceship.cache.database.DefaultSpaceXDataBase
import com.rmcgoff.watchthisspaceship.cache.database.SpaceXDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    companion object {
        @Provides
        @Singleton
        fun providesRoomDataBase(
            @ApplicationContext context: Context
        ): SpaceXDataBase {
            return Room.databaseBuilder(
                context,
                DefaultSpaceXDataBase::class.java, "space-x-db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}