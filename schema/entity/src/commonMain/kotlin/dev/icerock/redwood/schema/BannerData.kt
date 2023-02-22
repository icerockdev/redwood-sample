package dev.icerock.redwood.schema

import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.image.ImageDesc

data class BannerData(
    val placeholder: ImageResource?,
    val image: ImageDesc?,
    val text: StringDesc,
    val onClick: () -> Unit
)

data class BannerColumnData(
    val placeholder: ImageResource?,
    val image: ImageDesc?,
    val textTitle: String,
    val data: String,
    val textDescription: String,
    val onClick: () -> Unit
)