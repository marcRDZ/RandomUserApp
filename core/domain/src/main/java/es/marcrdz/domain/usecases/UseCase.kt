package es.marcrdz.domain.usecases

import arrow.core.Either
import es.marcrdz.domain.models.Fail

interface UseCase<in T, out S> {

    suspend operator fun invoke(param: T? = null): Either<Fail, S>

}
