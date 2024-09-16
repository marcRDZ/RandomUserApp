package es.marcrdz.datasource.services

import arrow.core.Either
import es.marcrdz.datasource.client.ClientConfig
import es.marcrdz.datasource.client.EitherCallAdapterFactory
import es.marcrdz.datasource.models.UserResponseDto
import es.marcrdz.domain.models.Fail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {

    @GET("/api")
    suspend fun getUsers(@Query("results") results: Int): Either<Fail, UserResponseDto>

}

internal class UsersServiceImpl(config: ClientConfig) : UsersService by Retrofit.Builder()
    .baseUrl(config.rootUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(EitherCallAdapterFactory())
    .client(
        OkHttpClient.Builder()
            .apply { config.okHttpConfig }
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            ).build()
    )
    .build()
    .create(UsersService::class.java)
