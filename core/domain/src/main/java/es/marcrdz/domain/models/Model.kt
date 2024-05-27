package es.marcrdz.domain.models

sealed class Fail {
    data object Unknown : Fail()
    data object Network : Fail()
    data object Unauthorized : Fail()
    data object Forbidden : Fail()
    data object NoData : Fail()
    data class Exception(val code: String, val msg: String) : Fail()
}

data class Item(
    val id: String,
    val attributes: Attributes
)

data class Attributes(
    val name: String,
    val description: String,
    val imgUrl: String
)
