package es.marcrdz.data.repositories

import arrow.core.Either
import es.marcrdz.data.DataContract
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.models.Fail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UsersRepositoryTest {

    @MockK
    lateinit var cacheDataSource: DataContract.UserDataSource.Cache

    @MockK
    lateinit var remoteDataSource: DataContract.UserDataSource.Remote

    private val repository: DomainContract.UsersRepository by lazy {
        UsersRepository(
            remoteDataSource,
            cacheDataSource
        )
    }

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `When items are cached fetchUsers returns data`() = runTest {
        //given
        coEvery { cacheDataSource.getUsers() } returns Either.right(emptyList())
        //when
        val result = repository.fetchUsers()
        //then
        Assert.assertTrue(result.isRight())
        coVerify { cacheDataSource.getUsers() }

    }

    @Test
    fun `When no items are cached, loadUsers is successful, saveItem executed and fetchUsers returns data`() =
        runTest {
            //given
            coEvery { cacheDataSource.getUsers() } returns Either.left(Fail.NoData)
            coEvery { remoteDataSource.loadUsers() } returns Either.right(emptyList())
            coEvery { cacheDataSource.saveUsers(any()) } returns Either.right(true)
            //when
            val result = repository.fetchUsers()
            //then
            Assert.assertTrue(result.isRight())
            coVerifySequence {
                cacheDataSource.getUsers()
                remoteDataSource.loadUsers()
                cacheDataSource.saveUsers(any())
            }
        }

    @Test
    fun `When no items are cached, loadUsers is unsuccessful, saveItem is not executed and fetchUsers returns fail`() =
        runTest {
            //given
            coEvery { cacheDataSource.getUsers() } returns Either.left(Fail.NoData)
            coEvery { remoteDataSource.loadUsers() } returns Either.left(Fail.Network)
            //when
            val result = repository.fetchUsers()
            //then
            Assert.assertTrue((result as? Either.Left)?.a is Fail.Network)
            coVerifySequence {
                cacheDataSource.getUsers()
                remoteDataSource.loadUsers()
            }
        }


    @Test
    fun `When clearUsers is executed returns data`() = runTest {
        //given
        coEvery { cacheDataSource.clearUsers() } returns Either.right(true)
        //when
        val result = repository.clearUsers()
        //then
        Assert.assertTrue(result.isRight())
        coVerify { cacheDataSource.clearUsers() }

    }

}
