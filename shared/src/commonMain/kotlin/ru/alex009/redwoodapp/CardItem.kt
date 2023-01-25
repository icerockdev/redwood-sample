package ru.alex009.redwoodapp

data class CardItem(
    val data: String,
    val text: String,
    val isLike: Boolean,
    val onClick: ()->Unit
)
