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
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ItemsRepositoryTest {

    @MockK
    lateinit var cacheDataSource: DataContract.ItemDataSource.Cache

    @MockK
    lateinit var remoteDataSource: DataContract.ItemDataSource.Remote

    private val repository: DomainContract.ItemsRepository by lazy {
        ItemsRepository(
            remoteDataSource,
            cacheDataSource
        )
    }

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `When items are cached fetchItems returns data`() = runTest {
        //given
        coEvery { cacheDataSource.getItems() } returns Either.right(emptyList())
        //when
        val result = repository.fetchItems()
        //then
        Assert.assertTrue(result.isRight())
        coVerify { cacheDataSource.getItems() }

    }

    @Test
    fun `When no items are cached, loadItems is successful, saveItem executed and fetchItems returns data`() =
        runTest {
            //given
            coEvery { cacheDataSource.getItems() } returns Either.left(Fail.NoData)
            coEvery { remoteDataSource.loadItems() } returns Either.right(emptyList())
            coEvery { cacheDataSource.saveItems(any()) } returns Either.right(true)
            //when
            val result = repository.fetchItems()
            //then
            Assert.assertTrue(result.isRight())
            coVerifySequence {
                cacheDataSource.getItems()
                remoteDataSource.loadItems()
                cacheDataSource.saveItems(any())
            }
        }

    @Test
    fun `When no items are cached, loadItems is unsuccessful, saveItem is not executed and fetchItems returns fail`() =
        runTest {
            //given
            coEvery { cacheDataSource.getItems() } returns Either.left(Fail.NoData)
            coEvery { remoteDataSource.loadItems() } returns Either.left(Fail.Network)
            //when
            val result = repository.fetchItems()
            //then
            Assert.assertTrue((result as? Either.Left)?.a is Fail.Network)
            coVerifySequence {
                cacheDataSource.getItems()
                remoteDataSource.loadItems()
            }
        }


    @Test
    fun `When clearItems is executed returns data`() = runTest {
        //given
        coEvery { cacheDataSource.clearItems() } returns Either.right(true)
        //when
        val result = repository.clearItems()
        //then
        Assert.assertTrue(result.isRight())
        coVerify { cacheDataSource.clearItems() }

    }

}
