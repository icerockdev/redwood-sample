package dev.icerock.redwoodapp.screens.entity

data class CardItem(
    val data: String,
    val text: String,
    val isLike: Boolean,
    val onClick: ()->Unit
)
