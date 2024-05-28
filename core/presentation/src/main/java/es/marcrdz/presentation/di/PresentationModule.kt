package es.marcrdz.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.presentation.handlers.MainEventHandler
import es.marcrdz.presentation.handlers.MainEventHandlerImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class PresentationModule {

    @Binds
    abstract fun bindMainEventHandler(handler: MainEventHandlerImpl): MainEventHandler

}