package es.marcrdz.domain.models

import java.util.Date

sealed class Fail {
    data object Unknown : Fail()
    data object Network : Fail()
    data object Unauthorized : Fail()
    data object Forbidden : Fail()
    data object NoData : Fail()
    data class Exception(val code: String, val msg: String) : Fail()
}
data class User(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: Moment,
    val registered: Moment,
    val phone: String,
    val cell: String,
    val id: Pair<String, String>,
    val picture: Picture,
    val nat: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class Moment(
    val date: Date,
    val age: Int
)

data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: Int,
    val coordinates: Coordinates,
    val timezone: TimeZone
)

data class Name(
    val title: String,
    val first: String,
    val last: String
) {
    override fun toString(): String {
        return "$title $first $last"
    }
}

data class Street(
    val number: Int,
    val name: String
)

data class Coordinates(
    val latitude: Double,
    val longitude: Double
)

data class TimeZone(
    val offset: String,
    val description: String
)
data class Item(
    val id: String,
    val attributes: Attributes
)

data class Attributes(
    val name: String,
    val description: String,
    val imgUrl: String
)
