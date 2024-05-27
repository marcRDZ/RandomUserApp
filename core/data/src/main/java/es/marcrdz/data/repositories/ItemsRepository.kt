package es.marcrdz.data.repositories

import arrow.core.Either
import arrow.core.right
import es.marcrdz.data.DataContract
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.Item
import javax.inject.Inject

class ItemsRepository @Inject constructor(
    private val remoteDataSource: DataContract.ItemDataSource.Remote,
    private val cacheDataSource: DataContract.ItemDataSource.Cache
): DomainContract.ItemsRepository {

    override suspend fun fetchItems(): Either<Fail, List<Item>> =
        cacheDataSource.getItems().fold({ remoteDataSource.loadItems() }) { items ->
            cacheDataSource.saveItems(items)
            items.right()
        }

    override suspend fun clearItems(): Either<Fail, Boolean> =
        cacheDataSource.clearItems()

}
