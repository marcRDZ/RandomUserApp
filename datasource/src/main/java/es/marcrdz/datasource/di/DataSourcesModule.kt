package es.marcrdz.datasource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.data.DataContract
import es.marcrdz.datasource.sources.UsersCacheDataSource
import es.marcrdz.datasource.sources.UsersRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindsUsersRemoteDataSource(dataSource: UsersRemoteDataSource): DataContract.UserDataSource.Remote

    @Binds
    abstract fun bindsUsersCacheDataSource(dataSource: UsersCacheDataSource): DataContract.UserDataSource.Cache

}
