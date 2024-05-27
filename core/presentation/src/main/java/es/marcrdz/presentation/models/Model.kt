package es.marcrdz.presentation.models


interface Event

sealed class BackgroundState {
    data object Loading: BackgroundState()
    data object Idle: BackgroundState()
}

data class ScreenState(
    val backgroundState: BackgroundState = BackgroundState.Idle
)