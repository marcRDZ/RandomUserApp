package es.marcrdz.domain.usecases

import arrow.core.Either
import es.marcrdz.domain.DomainContract
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ClearUsersUcTest {

    @MockK
    lateinit var repository: DomainContract.UsersRepository

    private val clearUsersUc: UseCase<Nothing, Boolean> by lazy {
        ClearUsersUc(repository)
    }

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `When clearItemsUc is executed returns data`() = runTest {
        //given
        coEvery { repository.clearUsers() } returns Either.right(true)
        //when
        val result = clearUsersUc()
        //then
        assertTrue(result.isRight())
        coVerify { repository.clearUsers() }
    }

}
