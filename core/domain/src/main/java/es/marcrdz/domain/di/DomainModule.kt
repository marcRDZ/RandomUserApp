package es.marcrdz.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.domain.models.Fail
import es.marcrdz.domain.models.Item
import es.marcrdz.domain.usecases.ClearItemsUc
import es.marcrdz.domain.usecases.ClearItemsUseCase
import es.marcrdz.domain.usecases.FetchItemsUc
import es.marcrdz.domain.usecases.FetchItemsUseCase
import es.marcrdz.domain.usecases.UseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @FetchItemsUseCase
    @Binds
    abstract fun bindsFetchItemsUseCase(useCase: FetchItemsUc): UseCase<Fail, List<Item>>

    @ClearItemsUseCase
    @Binds
    abstract fun bindsClearItemsUseCase(useCase: ClearItemsUc): UseCase<Fail, Boolean>

}
