package es.marcrdz.domain.usecases

import arrow.core.Either
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.User
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
annotation class FetchUsersUseCase

class FetchUsersUc @Inject constructor(
    private val repository: DomainContract.UsersRepository
): UseCase<@JvmSuppressWildcards Any, @JvmSuppressWildcards List<User>> {

    override suspend fun invoke(param: Any?): Either<Fail, List<User>> =
        repository.fetchUsers()

}