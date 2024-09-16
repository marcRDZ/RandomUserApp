package es.marcrdz.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.data.repositories.UsersRepository
import es.marcrdz.domain.DomainContract

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsUserRepository(repository: UsersRepository): DomainContract.UsersRepository

}
