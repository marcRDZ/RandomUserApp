package es.marcrdz.datasource.client

import arrow.core.Either
import es.marcrdz.domain.models.Fail
import okhttp3.Request
import okio.Timeout
import retrofit2.*
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Custom [CallAdapter.Factory] to handle Retrofit [Response] through [Either] type
 * parsing both exceptions and http error responses as [Fail]
 *
 * Original idea taken from:
 * https://proandroiddev.com/retrofit-calladapter-for-either-type-2145781e1c20
 */
internal class EitherCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null
        check(returnType is ParameterizedType) { "Return type must be a parameterized type." }

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != Either::class.java) return null
        check(responseType is ParameterizedType) { "Response type must be a parameterized type." }

        val leftType = getParameterUpperBound(0, responseType)
        if (getRawType(leftType) != Fail::class.java) return null

        val rightType = getParameterUpperBound(1, responseType)
        return EitherCallAdapter<Any>(rightType)
    }
}

private class EitherCallAdapter<R>(
    private val successType: Type
) : CallAdapter<R, Call<Either<Fail, R>>> {

    override fun adapt(call: Call<R>): Call<Either<Fail, R>> = EitherCall(call, successType)

    override fun responseType(): Type = successType
}

class EitherCall<R>(
    private val delegate: Call<R>,
    private val successType: Type
) : Call<Either<Fail, R>> {

    override fun enqueue(callback: Callback<Either<Fail, R>>) = delegate.enqueue(
        object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@EitherCall, Response.success(response.toEither()))
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val error = when (throwable) {
                    is IOException -> Fail.Network
                    else -> Fail.Unknown
                }
                callback.onResponse(this@EitherCall, Response.success(Either.Left(error)))
            }
        }
    )

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun clone(): Call<Either<Fail, R>> = EitherCall(delegate.clone(), successType)

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<Either<Fail, R>> =
        throw UnsupportedOperationException()

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    private fun <R> Response<R>.toEither(): Either<Fail, R> {
        // Http error response (4xx - 5xx)
        if (!isSuccessful) {
            val errorBody = errorBody()?.string() ?: ""
            return Either.Left(when (code()) {
                401 -> Fail.Unauthorized
                403 -> Fail.Forbidden
                404 -> Fail.NoData
                else -> Fail.Exception(code = "${code()}", msg = errorBody)
            })
        }

        // Http success response with body
        body()?.let { body -> return Either.Right(body) }

        // if we defined Unit as success type it means we expected no response body
        // e.g. in case of 204 No Content
        return if (successType == Unit::class.java) {
            @Suppress("UNCHECKED_CAST")
            Either.Right(Unit) as Either<Fail, R>
        } else {
            @Suppress("UNCHECKED_CAST")
            Either.Left(UnknownError("Response body was null")) as Either<Fail, R>
        }
    }
}