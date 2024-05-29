package es.marcrdz.ui.feature.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.marcrdz.presentation.handlers.MainData
import es.marcrdz.presentation.handlers.MainEvent
import es.marcrdz.presentation.handlers.MainEventHandler
import es.marcrdz.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    handler: MainEventHandler
): BaseViewModel<MainEvent, MainData>(
    initialState = MainData(items = emptyList()),
    handler = handler
) {

    init {
        viewModelScope.launch {
            handler.handleInit().collect {
                processState(it)
            }
        }
    }

    override fun onEvent(event: MainEvent)  {
        viewModelScope.launch {
            handler.handleEvent(event).collect {
                processState(it)
            }
        }
    }
}