package es.marcrdz.datasource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.data.DataContract
import es.marcrdz.datasource.sources.ItemsCacheDataSource
import es.marcrdz.datasource.sources.ItemsRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindsItemsRemoteDataSource(dataSource: ItemsRemoteDataSource): DataContract.ItemDataSource.Remote

    @Binds
    abstract fun bindsItemsCacheDataSource(dataSource: ItemsCacheDataSource): DataContract.ItemDataSource.Cache

}
