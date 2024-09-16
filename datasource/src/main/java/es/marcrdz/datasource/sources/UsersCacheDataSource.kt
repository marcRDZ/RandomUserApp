package es.marcrdz.datasource.sources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.marcrdz.data.DataContract
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersCacheDataSource @Inject constructor(): DataContract.UserDataSource.Cache {

    private var usersCache: List<User>? = null

    override suspend fun getUsers(): Either<Fail, List<User>> =
        usersCache?.right() ?: Fail.NoData.left()

    override suspend fun saveUsers(items: List<User>): Either<Fail, Boolean> {
        usersCache = items
        return (!usersCache.isNullOrEmpty()).right()
    }

    override suspend fun clearUsers(): Either<Fail, Boolean> {
        usersCache = null
        return (usersCache.isNullOrEmpty()).right()
    }

}
