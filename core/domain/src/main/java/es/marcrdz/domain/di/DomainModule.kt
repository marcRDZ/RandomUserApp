package es.marcrdz.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.domain.models.User
import es.marcrdz.domain.usecases.ClearUsersUc
import es.marcrdz.domain.usecases.ClearUsersUseCase
import es.marcrdz.domain.usecases.FetchUsersUc
import es.marcrdz.domain.usecases.FetchUsersUseCase
import es.marcrdz.domain.usecases.UseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @FetchUsersUseCase
    @Binds
    abstract fun bindsFetchUsersUseCase(useCase: FetchUsersUc): UseCase<Nothing, List<User>>

    @ClearUsersUseCase
    @Binds
    abstract fun bindsClearUsersUseCase(useCase: ClearUsersUc): UseCase<Nothing, Boolean>

}
