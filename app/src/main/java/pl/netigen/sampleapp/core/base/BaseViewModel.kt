package pl.netigen.sampleapp.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pl.netigen.sampleapp.core.data.ViewEvent
import pl.netigen.sampleapp.core.data.ViewState
import java.lang.Exception

abstract class BaseViewModel<STATE : ViewState, Event : ViewEvent>() : ViewModel() {

    private val initialState: STATE by lazy { setInitialState() }
    abstract fun setInitialState(): STATE

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()
    val currentState: STATE get() = state.value
    protected fun setState(update: (old: STATE) -> STATE): STATE = _state.updateAndGet(update)
    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    abstract fun handleEvents(event: Event)

    fun subscribeToEvents() {
        viewModelScope.launch {
            try {
                _event.collect {
                    handleEvents(it)
                }
            } catch (e:Exception){

            }
        }
    }

}
