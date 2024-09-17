package es.marcrdz.presentation.handlers

import es.marcrdz.domain.models.User
import es.marcrdz.domain.usecases.ClearUsersUseCase
import es.marcrdz.domain.usecases.FetchUsersUseCase
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
    @FetchUsersUseCase private val fetchItemsUc: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards List<User>>,
    @ClearUsersUseCase private val clearItemsUc: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards Boolean>
) : MainEventHandler {

    override suspend fun handleInit(): Flow<UIState<MainData>> = fetchUsers()

    override suspend fun handleEvent(event: MainEvent): Flow<UIState<MainData>> =
        when (event) {

            MainEvent.RefreshOnSwipe -> flow {
                emit(BackgroundState.Loading)
                clearItemsUc()
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

            MainEvent.RetryOnError -> fetchUsers()
            is MainEvent.OnUserSelected -> flow {
                emit(ScreenState(MainData(selectedUser = event.user)))
            }
        }

    private fun fetchUsers(): Flow<UIState<MainData>> = flow {
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
    data object RefreshOnSwipe : MainEvent()
    data object RetryOnError : MainEvent()
    data class OnUserSelected(val user: User?) : MainEvent()
}

data class MainData(
    val users: List<User> = emptyList(),
    val selectedUser: User? = null
) : Data