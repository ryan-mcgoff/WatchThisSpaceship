package com.rmcgoff.watchthisspaceship.homeui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmcgoff.watchthisspaceship.domain.DataResult
import com.rmcgoff.watchthisspaceship.domain.usescases.SpaceXCompanyUseCase
import com.rmcgoff.watchthisspaceship.domain.usescases.SpaceXLaunchesUseCase
import com.rmcgoff.watchthisspaceship.homeui.dialog.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val stateReducer: HomeStateReducer,
    private val spaceXLaunches: SpaceXLaunchesUseCase,
    private val spaceXCompanyInfo: SpaceXCompanyUseCase
) : ViewModel() {
    val state = stateReducer.state

    fun filterLaunches(filter: Filter) = retrieveLaunches(filter)

    init {
        retrieveLaunches()
        retrieveCompanyInfo()
    }

    private fun retrieveLaunches(filter: Filter = Filter.ASCENDING) {
        spaceXLaunches.doWork(filter).onEach { dataResult ->
            when (dataResult) {
                is DataResult.Loading -> {
                    stateReducer.sendEvent(HomeEvent.LoadingLaunches)
                }
                is DataResult.Success -> {
                    stateReducer.sendEvent(HomeEvent.LaunchesLoaded(
                        launches = dataResult.data,
                        filterUsed = filter
                    ))
                }
                is DataResult.Error -> {
                    stateReducer.sendEvent(HomeEvent.LaunchesError(dataResult.errorMessage))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun retrieveCompanyInfo() {
        spaceXCompanyInfo.doWork().onEach { dataResult ->
            when (dataResult) {
                is DataResult.Loading -> {
                    stateReducer.sendEvent(HomeEvent.LoadingCompanyInfo)
                }
                is DataResult.Success -> {
                    stateReducer.sendEvent(HomeEvent.CompanyInfoLoaded(dataResult.data))
                }
                is DataResult.Error -> {
                    stateReducer.sendEvent(HomeEvent.CompanyInfoError(dataResult.errorMessage))
                }
            }
        }.launchIn(viewModelScope)
    }
}