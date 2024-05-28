package es.marcrdz.presentation

import es.marcrdz.presentation.models.Data
import es.marcrdz.presentation.models.Event
import es.marcrdz.presentation.models.UIState
import kotlinx.coroutines.flow.Flow

interface PresentationContract {

    interface EventFlowHandler<in E : Event, out D : Data> {

        suspend fun handleInit(): Flow<UIState<D>>

        suspend fun handleEvent(event: E): Flow<UIState<D>>

    }

}