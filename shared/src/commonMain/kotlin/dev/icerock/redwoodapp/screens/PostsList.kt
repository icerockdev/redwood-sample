package dev.icerock.redwoodapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.resources.desc.desc
import org.example.library.MR
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.ImageButton
import dev.icerock.redwood.schema.compose.Space
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.NEWS_LIST
import dev.icerock.redwoodapp.SimpleListViewModel
import dev.icerock.redwoodapp.ToolabrArgs
import dev.icerock.redwoodapp.ViewModelOwner
import dev.icerock.redwoodapp.getViewModel
import dev.icerock.redwoodapp.navigation.ScreenSettings
import dev.icerock.redwoodapp.screens.entity.CardItem

@Composable
fun PostsList(
    screenSettings: ScreenSettings<ToolabrArgs>,
    viewModelOwner: ViewModelOwner,
    routeToCreate: (String, String) -> Unit,
) {
    LaunchedEffect(screenSettings) {
        screenSettings.setToolbarData(ToolabrArgs.Simple("Posts".desc()))
    }
    val viewModel: SimpleListViewModel = getViewModel(viewModelOwner) {
        SimpleListViewModel()
    }

    val itemsList by remember {
        mutableStateOf(
            viewModel.listData.mapIndexed { index, it ->
                CardItem(
                    data = it.first,
                    text = it.second,
                    isLike = index % 2 == 0,
                ) {
                    routeToCreate(it.first, it.second)
                }
            }
        )
    }

    Column(
        padding = Padding(horizontal = 16),
        width = Constraint.Fill,
        height = Constraint.Fill
    ) {
        Column(
            width = Constraint.Fill,
            overflow = Overflow.Scroll
        ) {
            for (cardItem in itemsList) {
                Item(
                    data = cardItem.data,
                    text = cardItem.text,
                    isLike = cardItem.isLike,
                    onClick = cardItem.onClick
                )
            }
            Space(
                background = Color(0x00000000),
                width = 0,
                height = 80
            )
        }
    }
}

@Composable
fun Item(data: String, text: String, isLike: Boolean, onClick: () -> Unit) {
    Column(padding = Padding(top = 16)) {
        Card(
            onClick = {
                onClick()
            },
            child = {
                Column(
                    padding = Padding(top = 16, start = 16, bottom = 8, end = 16)
                ) {
                    Text(
                        text = data,
                        isSingleLine = true,
                        textType = TextType.Secondary,
                        layoutModifier = LayoutModifier.padding(Padding(bottom = 0))
                    )
                    Text(
                        text = text,
                        isSingleLine = false,
                        textType = TextType.Primary,
                        layoutModifier = LayoutModifier.padding(Padding(bottom = 0))
                    )
                    Row(
                        width = Constraint.Fill,
                        horizontalAlignment = MainAxisAlignment.End
                    ) {
                        var like: Int by remember { mutableStateOf(16) }
                        var dislike: Int by remember { mutableStateOf(7) }
                        var isLiked: Boolean? by remember { mutableStateOf(isLike) }
                        ImageButton(
                            text = like.toString().desc(),
                            icon = if (isLiked == true) MR.images.like_cliked else MR.images.like,
                            isClicked = isLiked == true,
                            onClick = {
                                when (isLiked) {
                                    null -> {
                                        like += 1
                                        isLiked = true
                                    }
                                    true -> {
                                        like -= 1
                                        isLiked = null
                                    }
                                    else -> {
                                        like += 1
                                        dislike -= 1
                                        isLiked = true
                                    }
                                }

                            },
                            layoutModifier = LayoutModifier.padding(Padding(end = 8))
                        )
                        ImageButton(
                            text = dislike.toString().desc(),
                            icon = if (isLiked == false) MR.images.dislike_cliked else MR.images.dislike,
                            isClicked = isLiked == false,
                            onClick = {
                                when (isLiked) {
                                    null -> {
                                        dislike += 1
                                        isLiked = false
                                    }
                                    false -> {
                                        dislike -= 1
                                        isLiked = null
                                    }
                                    else -> {
                                        like -= 1
                                        dislike += 1
                                        isLiked = false
                                    }
                                }
                            },
                        )
                    }
                }
            })
    }
}

