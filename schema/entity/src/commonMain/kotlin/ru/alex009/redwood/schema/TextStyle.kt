package ru.alex009.redwood.schema

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.FontResource

data class TextStyle (
    val textSize: Int,
    val textColor: Color,
    val textFont: FontResource
)

enum class ButtonType {
    Primary,
    Secondary,
    Action
}