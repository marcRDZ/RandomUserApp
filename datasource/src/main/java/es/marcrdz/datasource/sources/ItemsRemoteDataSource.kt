package es.marcrdz.datasource.sources

import arrow.core.Either
import es.marcrdz.data.DataContract
import es.marcrdz.datasource.mappers.itemDtoMapper
import es.marcrdz.datasource.services.ItemsService
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.Item
import javax.inject.Inject

class ItemsRemoteDataSource @Inject constructor(
    private val itemsService: ItemsService
) : DataContract.ItemDataSource.Remote {

    override suspend fun loadItems(): Either<Fail, List<Item>> =
        itemsService.getItems().map { response ->
            response.dataCollection?.map {
                itemDtoMapper(it.item)
            }.orEmpty()
        }

}
