package es.marcrdz.presentation.models

import es.marcrdz.domain.models.Fail


interface Event

interface Data

sealed class UIState<out T : Data>

data class FailState(val fail: Fail) : UIState<Nothing>()
class ScreenState<out T : Data>(val data: T) : UIState<T>()

sealed class BackgroundState: UIState<Nothing>() {
    data object Loading: BackgroundState()
    data object Idle: BackgroundState()
}
