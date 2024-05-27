package es.marcrdz.domain

import arrow.core.Either
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.Item

interface DomainContract {

    interface ItemsRepository {

        suspend fun fetchItems(): Either<Fail, List<Item>>

        suspend fun clearItems(): Either<Fail, Boolean>

    }
}