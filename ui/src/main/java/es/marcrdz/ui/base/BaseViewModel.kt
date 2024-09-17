package es.marcrdz.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import es.marcrdz.domain.models.Fail
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.models.BackgroundState
import es.marcrdz.presentation.models.Data
import es.marcrdz.presentation.models.Event
import es.marcrdz.presentation.models.FailState
import es.marcrdz.presentation.models.ScreenState
import es.marcrdz.presentation.models.UIState

abstract class BaseViewModel<E : Event, D : Data>(
    initialState: D,
    protected val handler: PresentationContract.EventFlowHandler<E, D>
) : ViewModel() {

    private val _backgroundState: MutableState<BackgroundState> =
        mutableStateOf(value = BackgroundState.Idle)
    val backgroundState: State<BackgroundState>
        get() = _backgroundState

    private val _failState: MutableState<Fail?> = mutableStateOf(value = null)
    val failState: State<Fail?>
        get() = _failState

    private val _screenState: MutableState<D> = mutableStateOf(value = initialState)
    val screenState: State<D>
        get() = _screenState

    abstract fun onEvent(event: E)

    protected fun processState(
        uiState: UIState<D>,
        process: D.(newState: D) -> D = { it }
    ) {
        when (uiState) {
            is BackgroundState -> _backgroundState.value = uiState
            is FailState -> _failState.value = uiState.fail
            is ScreenState -> _screenState.value = process.invoke(_screenState.value, uiState.data)
        }
    }

}
