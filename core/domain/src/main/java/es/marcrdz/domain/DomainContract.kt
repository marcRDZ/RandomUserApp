package es.marcrdz.domain

import arrow.core.Either
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.User

interface DomainContract {

    interface UsersRepository {

        suspend fun fetchUsers(): Either<Fail, List<User>>

        suspend fun clearUsers(): Either<Fail, Boolean>

    }
}