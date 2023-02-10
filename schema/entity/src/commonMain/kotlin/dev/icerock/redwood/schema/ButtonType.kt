package dev.icerock.redwood.schema

enum class ButtonType {
    Primary,
    Secondary,
    Text,
    Tonal,
}

sealed class Size {
    object Wrap : Size()
    object Fill : Size()
    data class Const(val value: Int) : Size()
}