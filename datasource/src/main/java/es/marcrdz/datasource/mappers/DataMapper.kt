package es.marcrdz.datasource.mappers

import es.marcrdz.datasource.models.AttributesDto
import es.marcrdz.datasource.models.ItemDto
import es.marcrdz.domain.models.Attributes
import es.marcrdz.domain.models.Item

val itemDtoMapper: (ItemDto?) -> Item = {
    Item(
        id = it?.id.orEmpty(),
        attributes = attributesDtoMapper(it?.attributes)
    )
}

val attributesDtoMapper: (AttributesDto?) -> Attributes = {
    Attributes(
        name = it?.name.orEmpty(),
        description = it?.description.orEmpty(),
        imgUrl = it?.imageInfo?.imageUrl.orEmpty()
    )
}
