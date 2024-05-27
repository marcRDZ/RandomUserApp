package es.marcrdz.data

import arrow.core.Either
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.Item

interface DataContract {

    interface ItemDataSource {

        interface Remote {
            suspend fun loadItems(): Either<Fail, List<Item>>
        }

        interface Cache {
            suspend fun getItems(): Either<Fail, List<Item>>
            suspend fun saveItems(items: List<Item>): Either<Fail, Boolean>
            suspend fun clearItems(): Either<Fail, Boolean>
        }
    }

}