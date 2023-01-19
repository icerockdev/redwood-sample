package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
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
import ru.alex009.redwood.schema.ButtonType
import ru.alex009.redwood.schema.TextType
import ru.alex009.redwood.schema.compose.Button
import ru.alex009.redwood.schema.compose.Card
import ru.alex009.redwood.schema.compose.ImageButton
import ru.alex009.redwood.schema.compose.Stack
import ru.alex009.redwood.schema.compose.Text

@Composable
fun PostsList(routeToCreate: () -> Unit) {
    Column(
        padding = Padding(16),
        width = Constraint.Fill,
        height = Constraint.Fill
    ) {
        Stack(
            child1 = {
                Column(overflow = Overflow.Scroll) {
                    repeat(10) {
                        Item(
                            data = "31 Сентября 2022 в 12:01",
                            text = "Никого не смущает огромная популяция кенгуру, которых в австралии почти столько же, сколько и людей, проживающих там? ",
                            isLike = true
                        )
                    }
                    /*Card {
                        Column(
                            padding = Padding(16),
                            width = Constraint.Fill,
                            height = Constraint.Wrap,
                            horizontalAlignment = CrossAxisAlignment.Center
                        ) {
                            var text: String by remember { mutableStateOf("some text") }
                            var isButtonClicked: Boolean by remember { mutableStateOf(false) }

                            TextInput(
                                state = text,
                                hint = "super text",
                                onChange = { text = it }
                            )

                            Row(
                                width = Constraint.Fill,
                                horizontalAlignment = MainAxisAlignment.SpaceBetween
                            ) {
                                Text(text = "left $text", textType = TextType.Secondary)
                                Text(text = "right $text")
                            }

                            Button(
                                text = "press me",
                                buttonType = ButtonType.Primary,
                                onClick = { text = "Hi!" }
                            )

                            ImageButton(
                                text = "16",
                                icon = null,
                                isClicked = isButtonClicked,
                                onClick = {
                                    isButtonClicked = !isButtonClicked
                                }
                            )
                        }
                    }*/
                }
            },
            child2 = {
                Column(
                    height = Constraint.Wrap
                ) {
                    Button(
                        text = "Предложить пост",
                        buttonType = ButtonType.Primary,
                        onClick = { }
                    )
                }
            }
        )
    }
}

@Composable
fun Item(data: String, text: String, isLike: Boolean) {
    var _isLike: Boolean by remember { mutableStateOf(isLike) }
    Column(padding = Padding(bottom = 16)) {
        Card {
            Column(
                padding = Padding(16)
            ) {
                Text(
                    text = data,
                    isSingleLine = true,
                    textType = TextType.Secondary,
                    layoutModifier = LayoutModifier.padding(Padding(bottom = 16))
                )
                Text(
                    text = text,
                    isSingleLine = false,
                    textType = TextType.Primary,
                    layoutModifier = LayoutModifier.padding(Padding(bottom = 16))
                )
                Row(
                    width = Constraint.Fill,
                    horizontalAlignment = MainAxisAlignment.End
                ) {
                    ImageButton(
                        text = "16",
                        icon = null,//MR.images.like,
                        isClicked = _isLike,
                        onClick = { _isLike = !_isLike },
                        layoutModifier = LayoutModifier.padding(Padding(end = 8))
                    )
                    ImageButton(
                        text = "9",
                        icon = null,
                        isClicked = _isLike,
                        onClick = { _isLike = !_isLike },
                    )
                }
            }
        }
    }
}

@Composable
fun CreateScreen(onSuccess: () -> Unit) {
    Column {
//        TextInput()
        Button(
            text = "done",
            buttonType = ButtonType.Action,
            onClick = {
                onSuccess()
            }
        )
    }
}

fun mainApp(): NavigationRoot {
    return navigation {
        register("list") { navigator ->
            PostsList(routeToCreate = { navigator.navigate("create") })
        }
        register("create") { navigator ->
            CreateScreen(onSuccess = { navigator.popUp() })
        }
    }
}

expect class NavigationRoot

interface Navigator {
    fun navigate(uri: String)
    fun popUp()
}

interface NavigationDsl {
    fun register(uri: String, screen: @Composable (Navigator) -> Unit)
}

expect fun navigation(block: NavigationDsl.() -> Unit): NavigationRoot
