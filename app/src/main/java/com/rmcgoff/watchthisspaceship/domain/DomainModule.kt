package com.rmcgoff.watchthisspaceship.domain

import com.rmcgoff.watchthisspaceship.domain.usescases.DefaultSpaceXCompanyUseCase
import com.rmcgoff.watchthisspaceship.domain.usescases.DefaultSpaceXLaunchesUseCase
import com.rmcgoff.watchthisspaceship.domain.usescases.SpaceXCompanyUseCase
import com.rmcgoff.watchthisspaceship.domain.usescases.SpaceXLaunchesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    @Singleton
    abstract fun bindsDefaultSpaceXRepo(default: DefaultSpaceXRepository): SpaceXRepository

    @Binds
    @Singleton
    abstract fun bindsDefaultSpaceLaunchUseCase(default: DefaultSpaceXLaunchesUseCase): SpaceXLaunchesUseCase

    @Binds
    @Singleton
    abstract fun bindsDefaultSpaceCompanyUseCase(default: DefaultSpaceXCompanyUseCase): SpaceXCompanyUseCase
}