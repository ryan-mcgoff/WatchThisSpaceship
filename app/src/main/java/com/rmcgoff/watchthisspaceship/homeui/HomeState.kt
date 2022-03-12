package com.rmcgoff.watchthisspaceship.homeui

import com.rmcgoff.watchthisspaceship.arc.Event
import com.rmcgoff.watchthisspaceship.arc.StateReducer
import com.rmcgoff.watchthisspaceship.arc.ViewState
import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity
import javax.inject.Inject

class HomeStateReducer @Inject constructor() : StateReducer<HomeViewState, HomeEvent>(initialViewState = HomeViewState.EMPTY) {
    override fun reduce(oldState: HomeViewState, event: HomeEvent): HomeViewState {
        return when (event) {
            is HomeEvent.LaunchesError -> {
                oldState.copy(
                    launches = oldState.launches.copy(loading = false),
                    alertMessage = event.errorMessage
                )
            }
            is HomeEvent.LaunchesLoaded -> {
                oldState.copy(
                    launches = oldState.launches.copy(loading = true, launches = event.launches),
                    alertMessage = null
                )
            }
            is HomeEvent.LoadingLaunches -> {
                oldState.copy(
                    launches = oldState.launches.copy(loading = true),
                    alertMessage = null
                )
            }
            is HomeEvent.CompanyInfoError -> {
                oldState.copy(
                    companyInfo = oldState.companyInfo.copy(loading = true),
                    alertMessage = event.errorMessage
                )
            }
            is HomeEvent.CompanyInfoLoaded -> {
                oldState.copy(
                    companyInfo = oldState.companyInfo.copy(loading = false, companyInfo = event.info)
                )
            }
            is HomeEvent.LoadingCompanyInfo -> {
                oldState.copy(
                    companyInfo = oldState.companyInfo.copy(loading = true)
                )
            }
        }
    }
}

// ViewState
data class HomeViewState(
    val companyInfo: CompanyInfoViewState,
    val launches: LaunchesViewState,
    val alertMessage: String? = null
) : ViewState {
    companion object {
        val EMPTY = HomeViewState(companyInfo = CompanyInfoViewState(), launches = LaunchesViewState())
    }
}

data class CompanyInfoViewState(val loading: Boolean = false, val companyInfo: String = "")
data class LaunchesViewState(val loading: Boolean = false, val launches: List<LaunchEntity> = emptyList())

// State changed events
sealed class HomeEvent : Event {
    // Launches events
    object LoadingLaunches : HomeEvent()
    data class LaunchesLoaded(val launches: List<LaunchEntity>) : HomeEvent()
    data class LaunchesError(val errorMessage: String) : HomeEvent()

    // Company info events
    object LoadingCompanyInfo : HomeEvent()
    data class CompanyInfoLoaded(val info: String) : HomeEvent()
    data class CompanyInfoError(val errorMessage: String) : HomeEvent()
}