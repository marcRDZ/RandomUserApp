package es.marcrdz.presentation.handlers

import app.cash.turbine.test
import arrow.core.Either
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.Item
import es.marcrdz.domain.usecases.ClearItemsUc
import es.marcrdz.domain.usecases.UseCase
import es.marcrdz.presentation.models.BackgroundState
import es.marcrdz.presentation.models.FailState
import es.marcrdz.presentation.models.ScreenState
import es.marcrdz.presentation.models.UIState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class MainEventHandlerTest {

    @MockK
    lateinit var clearItemsUc: UseCase<Nothing, Boolean>

    @MockK
    lateinit var fetchItemsUc: UseCase<Nothing, List<Item>>

    private val mainEventHandler: MainEventHandler by lazy {
        MainEventHandlerImpl(fetchItemsUc, clearItemsUc)
    }

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `When handleOnInit is executed and fetchItems is successful, flow of data is returned`() =
        runTest {
            //given
            coEvery { fetchItemsUc() } returns Either.Right(emptyList())
            //when
            val result = mainEventHandler.handleInit()
            //then
            result.test {
                Assert.assertTrue(awaitItem() is BackgroundState.Loading)
                Assert.assertTrue(awaitItem() is BackgroundState.Idle)
                Assert.assertTrue(awaitItem() is ScreenState<MainData>)
                awaitComplete()
            }
            coVerify { fetchItemsUc() }
        }

    @Test
    fun `When handleOnInit is executed and fetchItems is unsuccessful, flow of fail is returned`() =
        runTest {
            //given
            coEvery { fetchItemsUc() } returns Either.left(Fail.Unknown)
            //when
            val result = mainEventHandler.handleInit()
            //then
            result.test {
                Assert.assertTrue(awaitItem() is BackgroundState.Loading)
                Assert.assertTrue(awaitItem() is BackgroundState.Idle)
                Assert.assertTrue((awaitItem() as FailState).fail is Fail.Unknown)
                awaitComplete()
            }
            coVerify { fetchItemsUc() }
        }

    @Test
    fun `When handleOnEvent is executed for RetryOnError and fetchItems is successful, flow of data is returned`() =
        runTest {
            //given
            coEvery { fetchItemsUc() } returns Either.Right(emptyList())
            //when
            val result = mainEventHandler.handleEvent(MainEvent.RetryOnError)
            //then
            result.test {
                Assert.assertTrue(awaitItem() is BackgroundState.Loading)
                Assert.assertTrue(awaitItem() is BackgroundState.Idle)
                Assert.assertTrue(awaitItem() is ScreenState<MainData>)
                awaitComplete()
            }
            coVerify { fetchItemsUc() }
        }

    @Test
    fun `When handleOnEvent is executed for RetryOnError and fetchItems is unsuccessful, flow of data is returned`() =
        runTest {
            //given
            coEvery { fetchItemsUc() } returns Either.left(Fail.Unknown)
            //when
            val result = mainEventHandler.handleEvent(MainEvent.RetryOnError)
            //then
            result.test {
                Assert.assertTrue(awaitItem() is BackgroundState.Loading)
                Assert.assertTrue(awaitItem() is BackgroundState.Idle)
                Assert.assertTrue((awaitItem() as FailState).fail is Fail.Unknown)
                awaitComplete()
            }
            coVerify { fetchItemsUc() }
        }

    @Test
    fun `When handleOnEvent is executed for RefreshOnSwipe and fetchItems is successful, flow of data is returned`() =
        runTest {
            //given
            coEvery { clearItemsUc() } returns Either.Right(true)
            coEvery { fetchItemsUc() } returns Either.Right(emptyList())
            //when
            val result = mainEventHandler.handleEvent(MainEvent.RefreshOnSwipe)
            //then
            result.test {
                Assert.assertTrue(awaitItem() is BackgroundState.Loading)
                Assert.assertTrue(awaitItem() is BackgroundState.Idle)
                Assert.assertTrue(awaitItem() is ScreenState<MainData>)
                awaitComplete()
            }
            coVerifySequence {
                clearItemsUc()
                fetchItemsUc()
            }
        }

    @Test
    fun `When handleOnEvent is executed for RefreshOnSwipe and fetchItems is unsuccessful, flow of data is returned`() =
        runTest {
            //given
            coEvery { clearItemsUc() } returns Either.Right(true)
            coEvery { fetchItemsUc() } returns Either.left(Fail.Unknown)
            //when
            val result = mainEventHandler.handleEvent(MainEvent.RefreshOnSwipe)
            //then
            result.test {
                Assert.assertTrue(awaitItem() is BackgroundState.Loading)
                Assert.assertTrue(awaitItem() is BackgroundState.Idle)
                Assert.assertTrue((awaitItem() as FailState).fail is Fail.Unknown)
                awaitComplete()
            }
            coVerifySequence {
                clearItemsUc()
                fetchItemsUc()
            }
        }

}
