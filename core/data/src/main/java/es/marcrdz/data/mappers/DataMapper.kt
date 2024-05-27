package es.marcrdz.data.mappers

import es.marcrdz.data.models.AttributesDto
import es.marcrdz.data.models.ItemDto
import es.marcrdz.domain.models.Attributes
import es.marcrdz.domain.models.Item

val itemDtoMapper: (ItemDto) -> Item = {
    Item(
        id = it.id,
        attributes = attributesDtoMapper(it.attributes)
    )
}

val attributesDtoMapper: (AttributesDto) -> Attributes = {
    Attributes(
        name = it.name,
        description = it.description,
        imgUrl = it.imageInfo.imageUrl
    )
}
