package com.rmcgoff.watchthisspaceship.homeui

import com.rmcgoff.watchthisspaceship.arc.Event
import com.rmcgoff.watchthisspaceship.arc.StateReducer
import com.rmcgoff.watchthisspaceship.arc.ViewState
import com.rmcgoff.watchthisspaceship.cache.entity.LaunchEntity
import com.rmcgoff.watchthisspaceship.homeui.dialog.Filter
import javax.inject.Inject

class HomeStateReducer @Inject constructor() : StateReducer<HomeViewState, HomeEvent>(initialViewState = HomeViewState.EMPTY) {
    override fun reduce(oldState: HomeViewState, event: HomeEvent): HomeViewState {
        return when (event) {
            is HomeEvent.LaunchesError -> {
                oldState.copy(
                    launchesViewState = oldState.launchesViewState.copy(loading = false),
                    alertMessage = event.errorMessage
                )
            }
            is HomeEvent.LaunchesLoaded -> {
                oldState.copy(
                    launchesViewState = oldState.launchesViewState.copy(
                        loading = false,
                        launches = event.launches
                    ),
                    alertMessage = null
                )
            }
            is HomeEvent.LoadingLaunches -> {
                oldState.copy(
                    launchesViewState = oldState.launchesViewState.copy(loading = true),
                    alertMessage = null
                )
            }
            is HomeEvent.CompanyInfoError -> {
                oldState.copy(
                    companyInfoViewState = oldState.companyInfoViewState.copy(loading = true),
                    alertMessage = event.errorMessage
                )
            }
            is HomeEvent.CompanyInfoLoaded -> {
                oldState.copy(
                    companyInfoViewState = oldState.companyInfoViewState.copy(
                        loading = false,
                        companyInfo = event.info
                    )
                )
            }
            is HomeEvent.LoadingCompanyInfo -> {
                oldState.copy(
                    companyInfoViewState = oldState.companyInfoViewState.copy(loading = true)
                )
            }
        }
    }
}

// ViewState
data class HomeViewState(
    val companyInfoViewState: CompanyInfoViewState,
    val launchesViewState: LaunchesViewState,
    val alertMessage: String? = null
) : ViewState {
    companion object {
        val EMPTY = HomeViewState(companyInfoViewState = CompanyInfoViewState(), launchesViewState = LaunchesViewState())
    }
}

data class CompanyInfoViewState(val loading: Boolean = true, val companyInfo: String = "")
data class LaunchesViewState(val loading: Boolean = true, val launches: List<LaunchEntity> = emptyList(), val filter: Filter = Filter.ASCENDING)

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