package pl.netigen.sampleapp.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import pl.netigen.sampleapp.core.data.State

abstract class BaseViewModel<STATE : State>(initialState: STATE) : ViewModel() {

    private val _state = MutableStateFlow(initialState)

    val state: StateFlow<STATE> = _state.asStateFlow()

    val currentState: STATE get() = state.value

    protected fun setState(update: (old: STATE) -> STATE): STATE = _state.updateAndGet(update)
}
