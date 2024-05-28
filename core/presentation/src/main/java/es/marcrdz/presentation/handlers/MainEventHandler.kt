package es.marcrdz.presentation.handlers

import es.marcrdz.domain.models.Item
import es.marcrdz.domain.usecases.ClearItemsUseCase
import es.marcrdz.domain.usecases.FetchItemsUseCase
import es.marcrdz.domain.usecases.UseCase
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.models.BackgroundState
import es.marcrdz.presentation.models.Data
import es.marcrdz.presentation.models.Event
import es.marcrdz.presentation.models.FailState
import es.marcrdz.presentation.models.ScreenState
import es.marcrdz.presentation.models.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface MainEventHandler : PresentationContract.EventFlowHandler<MainEvent, MainData>
class MainEventHandlerImpl @Inject constructor(
    @FetchItemsUseCase private val fetchItemsUc: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards List<Item>>,
    @ClearItemsUseCase private val clearItemsUc: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards Boolean>
) : MainEventHandler {

    override suspend fun handleInit(): Flow<UIState<MainData>> = fetchItems()

    override suspend fun handleEvent(event: MainEvent): Flow<UIState<MainData>> =
        when (event) {
            MainEvent.SwipeToRefresh -> flow {
                emit(BackgroundState.Loading)
                clearItemsUc().let {
                    emit(BackgroundState.Idle)
                    fetchItems()
                }
            }
        }

    private fun fetchItems(): Flow<UIState<MainData>> = flow {
        emit(BackgroundState.Loading)
        fetchItemsUc().let {
            emit(BackgroundState.Idle)
            emit(
                it.fold(
                    ifLeft = { fail -> FailState(fail) },
                    ifRight = { items -> ScreenState(MainData(items)) }
                )
            )
        }
    }

}

sealed class MainEvent : Event {
    data object SwipeToRefresh : MainEvent()
}

data class MainData(
    val items: List<Item>
) : Data