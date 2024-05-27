package es.marcrdz.data.models

data class ItemsResponseDto(
    val dataCollection: List<ItemDto>
)

data class ItemDto(
    val id: String,
    val attributes: AttributesDto
)

data class AttributesDto(
    val name: String,
    val description: String,
    val imageInfo: ImageInfoDto
)

data class ImageInfoDto(
    val imageUrl: String
)