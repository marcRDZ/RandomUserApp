package es.marcrdz.domain.usecases

import arrow.core.Either
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.models.Fail
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
annotation class ClearItemsUseCase

class ClearItemsUc @Inject constructor(
    private val repository: DomainContract.ItemsRepository
): UseCase<Any, Boolean> {

    override suspend fun invoke(param: Any?): Either<Fail, Boolean> =
        repository.clearItems()

}
