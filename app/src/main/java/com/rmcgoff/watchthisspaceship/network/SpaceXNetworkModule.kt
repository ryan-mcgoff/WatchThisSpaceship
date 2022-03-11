package com.rmcgoff.watchthisspaceship.network

import com.rmcgoff.watchthisspaceship.network.datasource.DefaultSpaceXNetworkDataSource
import com.rmcgoff.watchthisspaceship.network.datasource.SpaceXNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SpaceXNetworkModule {

    companion object {
        private val BASE_URL = "https://api.spacexdata.com/v4/"
        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            // 20 second timeout
            return OkHttpClient.Builder()
                .connectTimeout(20L, TimeUnit.SECONDS)
                .readTimeout(20L, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build()
        }

        @Provides
        fun provideRetroFit(
            okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun provideSpaceXService(retrofit: Retrofit) : SpaceXService {
            return retrofit.create(SpaceXService::class.java)
        }
    }

    @Binds
    internal abstract fun provideDefaultDataSource(bind: DefaultSpaceXNetworkDataSource): SpaceXNetworkDataSource
}