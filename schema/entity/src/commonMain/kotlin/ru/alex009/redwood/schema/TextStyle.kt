package ru.alex009.redwood.schema

import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.FontResource

data class TextStyle (
    val textSize: Int,
    val textColor: ColorResource,
    val textFont: FontResource
)