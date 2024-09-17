package es.marcrdz.datasource.mappers

import android.util.Log
import es.marcrdz.datasource.models.CoordinatesDto
import es.marcrdz.datasource.models.LocationDto
import es.marcrdz.datasource.models.LoginDto
import es.marcrdz.datasource.models.MomentDto
import es.marcrdz.datasource.models.NameDto
import es.marcrdz.datasource.models.PictureDto
import es.marcrdz.datasource.models.StreetDto
import es.marcrdz.datasource.models.TimeZoneDto
import es.marcrdz.datasource.models.UserDto
import es.marcrdz.domain.models.Coordinates
import es.marcrdz.domain.models.Location
import es.marcrdz.domain.models.Login
import es.marcrdz.domain.models.Moment
import es.marcrdz.domain.models.Name
import es.marcrdz.domain.models.Picture
import es.marcrdz.domain.models.Street
import es.marcrdz.domain.models.TimeZone
import es.marcrdz.domain.models.User
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val userDtoMapper: (UserDto?) -> User = {
    User(
        gender = it?.gender.orEmpty(),
        name = nameDtoMapper(it?.name),
        location = locationDtoMapper(it?.location),
        email = it?.email.orEmpty(),
        login = loginDtoMapper(it?.login),
        dob = momentDtoMapper(it?.dob),
        registered = momentDtoMapper(it?.registered),
        phone = it?.phone.orEmpty(),
        cell = it?.cell.orEmpty(),
        id = it?.id?.first.orEmpty() to it?.id?.second.orEmpty(),
        picture = pictureDtoMapper(it?.picture),
        nat = it?.nat.orEmpty()
    )
}

val pictureDtoMapper: (PictureDto?) -> Picture = {
    Picture(
        large = it?.large.orEmpty(),
        medium = it?.medium.orEmpty(),
        thumbnail = it?.thumbnail.orEmpty()
    )
}

fun MomentDto.getDate(): Date? {
    val defaultDateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US)
    return try {
        defaultDateFormatter.parse(date.orEmpty())
    } catch (e: ParseException) {
        Log.e("MomentDtoDateParsing", e.message.toString())
        null
    }
}

val momentDtoMapper: (MomentDto?) -> Moment = {
    Moment(
        date = it?.getDate() ?: Date(),
        age = it?.age ?: 0

    )
}

val loginDtoMapper: (LoginDto?) -> Login = {
    Login(
        uuid = it?.uuid.orEmpty(),
        username = it?.username.orEmpty(),
        password = it?.password.orEmpty(),
        salt = it?.salt.orEmpty(),
        md5 = it?.md5.orEmpty(),
        sha1 = it?.sha1.orEmpty(),
        sha256 = it?.sha256.orEmpty()
    )
}

val nameDtoMapper: (NameDto?) -> Name = {
    Name(
        title = it?.title.orEmpty(),
        first = it?.first.orEmpty(),
        last = it?.last.orEmpty()
    )
}

val locationDtoMapper: (LocationDto?) -> Location = {
    Location(
        street = streetDtoMapper(it?.street),
        city = it?.city.orEmpty(),
        state = it?.state.orEmpty(),
        country = it?.country.orEmpty(),
        postcode = it?.postcode.orEmpty(),
        coordinates = coordinatesDtoMapper(it?.coordinates),
        timezone = timeZoneDtoMapper(it?.timezone)
    )
}
val streetDtoMapper: (StreetDto?) -> Street = {
    Street(
        number = it?.number ?: 0,
        name = it?.name.orEmpty()
    )
}

val coordinatesDtoMapper: (CoordinatesDto?) -> Coordinates = {
    Coordinates(
        latitude = it?.latitude?.toDoubleOrNull() ?: 0.0,
        longitude = it?.longitude?.toDoubleOrNull() ?: 0.0
    )
}

val timeZoneDtoMapper: (TimeZoneDto?) -> TimeZone = {
    TimeZone(
        offset = it?.offset.orEmpty(),
        description = it?.description.orEmpty()
    )
}