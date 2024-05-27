package es.marcrdz.datasource.sources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.marcrdz.data.DataContract
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.Item

object ItemsCacheDataSource: DataContract.ItemDataSource.Cache {

    private var itemsCache: List<Item>? = null

    override suspend fun getItems(): Either<Fail, List<Item>> =
        itemsCache?.right() ?: Fail.NoData.left()

    override suspend fun saveItems(items: List<Item>): Either<Fail, Boolean> {
        itemsCache = items
        return (!itemsCache.isNullOrEmpty()).right()
    }

    override suspend fun clearItems(): Either<Fail, Boolean> {
        itemsCache = null
        return (itemsCache.isNullOrEmpty()).right()
    }

}
