package es.marcrdz.datasource.models

data class UserResponseDto(
    val results: List<UserDto>?
)

data class UserDto(
    val gender: String?,
    val name: NameDto?,
    val location: LocationDto?,
    val email: String?,
    val login: LoginDto?,
    val dob: MomentDto?,
    val registered: MomentDto?,
    val phone: String?,
    val cell: String?,
    val id: Pair<String?, String?>,
    val picture: PictureDto?,
    val nat: String?
)

data class PictureDto(
    val large: String?,
    val medium: String?,
    val thumbnail: String?
)

data class MomentDto(
    val date: String?,
    val age: Int?
)

data class LoginDto(
    val uuid: String?,
    val username: String?,
    val password: String?,
    val salt: String?,
    val md5: String?,
    val sha1: String?,
    val sha256: String?
)

data class LocationDto(
    val street: StreetDto?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: Int?,
    val coordinates: CoordinatesDto?,
    val timezone: TimeZoneDto?
)

data class NameDto(
    val title: String?,
    val first: String?,
    val last: String?
)

data class StreetDto(
    val number: Int?,
    val name: String?
)

data class CoordinatesDto(
    val latitude: String?,
    val longitude: String?
)

data class TimeZoneDto(
    val offset: String?,
    val description: String?
)

data class ItemWrapperDto(
    val item: ItemDto?
)

data class ItemDto(
    val id: String?,
    val attributes: AttributesDto?
)

data class AttributesDto(
    val name: String?,
    val description: String?,
    val imageInfo: ImageInfoDto?
)

data class ImageInfoDto(
    val imageUrl: String?
)