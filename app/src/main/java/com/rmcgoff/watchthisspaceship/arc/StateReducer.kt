package com.rmcgoff.watchthisspaceship.arc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * A reducer takes the current state and an incoming event, and reduces the event into a ViewState update to be observed by the ViewLayer
 * This construct has a lot of benefits. Firstly, it's very easy to see how the flow of the screen would work for someone unfamiliar
 * with the codebase. It's clear that only certain states can be mutated,
 * and those are only mutated by certain events. This also is extremely testable, as no mocking
 * (except for DTO classes) needs to be done to ensure the state transitions work properly.
 */
abstract class StateReducer<S : ViewState, E : Event>(private val initialViewState: S) {

    private val _state: MutableLiveData<S> = MutableLiveData(initialViewState)
    val state: LiveData<S>
        get() = _state

    fun sendEvent(event: E) {
        _state.value
        val newState = reduce(_state.value ?: initialViewState, event)
        updateState(newState)
    }

    private fun updateState(newState: S) {
        _state.postValue(newState)
    }

    // Returns
    abstract fun reduce(oldState: S, event: E): S
}

interface ViewState
interface Event