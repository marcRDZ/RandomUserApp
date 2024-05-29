package es.marcrdz.domain.usecases

import arrow.core.Either
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.Item
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
annotation class FetchItemsUseCase

class FetchItemsUc @Inject constructor(
    private val repository: DomainContract.ItemsRepository
): UseCase<@JvmSuppressWildcards Any, @JvmSuppressWildcards List<Item>> {

    override suspend fun invoke(param: Any?): Either<Fail, List<Item>> =
        repository.fetchItems()

}