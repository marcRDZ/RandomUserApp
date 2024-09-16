package es.marcrdz.datasource.sources

import arrow.core.Either
import es.marcrdz.data.DataContract
import es.marcrdz.datasource.mappers.userDtoMapper
import es.marcrdz.datasource.services.UsersService
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.User
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(
    private val usersService: UsersService
) : DataContract.UserDataSource.Remote {

    override suspend fun loadUsers(): Either<Fail, List<User>> =
        usersService.getUsers(100).map { response ->
            response.results?.map {
                userDtoMapper(it)
            }.orEmpty()
        }

}
