package com.rmcgoff.watchthisspaceship.data

import android.content.Context
import android.widget.Space
import androidx.room.Room
import com.rmcgoff.watchthisspaceship.data.database.DefaultSpaceXDataBase
import com.rmcgoff.watchthisspaceship.data.database.SpaceXDataBase
import dagger.Binds
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