package es.marcrdz.data.repositories

import arrow.core.Either
import arrow.core.right
import es.marcrdz.data.DataContract
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.User
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val remoteDataSource: DataContract.UserDataSource.Remote,
    private val cacheDataSource: DataContract.UserDataSource.Cache
): DomainContract.UsersRepository {

    override suspend fun fetchUsers(): Either<Fail, List<User>> =
        cacheDataSource.getUsers().fold(
            ifLeft = {
                remoteDataSource.loadUsers().map { users ->
                    cacheDataSource.saveUsers(users)
                    users
                }
            },
            ifRight = { it.right() }
        )

    override suspend fun clearUsers(): Either<Fail, Boolean> =
        cacheDataSource.clearUsers()

}
