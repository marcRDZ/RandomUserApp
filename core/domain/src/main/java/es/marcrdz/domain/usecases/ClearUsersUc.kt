package es.marcrdz.domain.usecases

import arrow.core.Either
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.models.Fail
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
annotation class ClearUsersUseCase

class ClearUsersUc @Inject constructor(
    private val repository: DomainContract.UsersRepository
): UseCase<@JvmSuppressWildcards Any, @JvmSuppressWildcards Boolean> {

    override suspend fun invoke(param: Any?): Either<Fail, Boolean> =
        repository.clearUsers()

}
