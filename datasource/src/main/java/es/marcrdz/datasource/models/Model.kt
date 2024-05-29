package es.marcrdz.datasource.models

data class ItemsResponseDto(
    val dataCollection: List<ItemWrapperDto>?
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