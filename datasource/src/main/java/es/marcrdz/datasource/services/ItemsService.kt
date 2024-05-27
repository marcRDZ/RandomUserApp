package es.marcrdz.datasource.services

import arrow.core.Either
import es.marcrdz.data.models.ItemsResponseDto
import es.marcrdz.datasource.client.ClientConfig
import es.marcrdz.datasource.client.EitherCallAdapterFactory
import es.marcrdz.domain.models.Fail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ItemsService {

    @GET("/v1/public/characters")
    suspend fun getItems(): Either<Fail, ItemsResponseDto>

}

internal class ItemsServiceImpl(config: ClientConfig) : ItemsService by Retrofit.Builder()
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
    .create(ItemsService::class.java)
