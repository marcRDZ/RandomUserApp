package es.marcrdz.data

import arrow.core.Either
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.User

interface DataContract {

    interface UserDataSource {

        interface Remote {
            suspend fun loadUsers(): Either<Fail, List<User>>
        }

        interface Cache {
            suspend fun getUsers(): Either<Fail, List<User>>
            suspend fun saveUsers(items: List<User>): Either<Fail, Boolean>
            suspend fun clearUsers(): Either<Fail, Boolean>
        }
    }

}