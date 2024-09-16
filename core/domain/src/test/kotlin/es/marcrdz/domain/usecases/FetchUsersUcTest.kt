package es.marcrdz.domain.usecases

import arrow.core.Either
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FetchUsersUcTest{

    @MockK
    lateinit var repository: DomainContract.UsersRepository

    private val fetchItemsUc: UseCase<Nothing, List<User>> by lazy {
        FetchUsersUc(repository)
    }

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `When fetchItemsUc is successful returns data`() = runTest {
        //given
        coEvery { repository.fetchUsers() } returns Either.right(emptyList())
        //when
        val result = fetchItemsUc()
        //then
        assertTrue(result.isRight())
        coVerify { repository.fetchUsers() }
    }

    @Test
    fun `When fetchItemsUc is unsuccessful returns fail`() = runTest {
        //given
        coEvery { repository.fetchUsers() } returns Either.left(Fail.Unknown)
        //when
        val result = fetchItemsUc()
        //then
        assertTrue((result as? Either.Left)?.a is Fail.Unknown)
        coVerify { repository.fetchUsers() }
    }

}
