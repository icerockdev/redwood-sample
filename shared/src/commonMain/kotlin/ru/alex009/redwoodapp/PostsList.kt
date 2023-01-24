package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.MainAxisAlignment
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
    val itemsList = remember {
        mutableStateListOf(
            CardItem(
                data = "1 Сентября 2022 в 12:01",
                text = "Никого не смущает огромная популяция кенгуру, которых в австралии почти столько же, сколько и людей, проживающих там? ",
                isLike = true
            ),
            CardItem(
                data = "2 Сентября 2022 в 12:01",
                text = "Никого не смущает огромная популяция кенгуру, которых в австралии почти столько же, сколько и людей, проживающих там? ",
                isLike = false
            ),
            CardItem(
                data = "3 Сентября 2022 в 12:01",
                text = "Никого не смущает огромная популяция кенгуру, которых в австралии почти столько же, сколько и людей, проживающих там? ",
                isLike = true
            ),
            CardItem(
                data = "4 Сентября 2022 в 12:01",
                text = "Никого не смущает огромная популяция кенгуру, которых в австралии почти столько же, сколько и людей, проживающих там? ",
                isLike = false
            ),
            CardItem(
                data = "31 Сентября 2022 в 12:01",
                text = "Никого не смущает огромная популяция кенгуру, которых в австралии почти столько же, сколько и людей, проживающих там? ",
                isLike = true
            )
        )
    }

    Column(
        padding = Padding(horizontal = 16),
        width = Constraint.Fill,
        height = Constraint.Fill
    ) {
        Stack(
            child1 = {
                Column(width = Constraint.Fill) {
                    for (cardItem in itemsList) {
                        Item(
                            data = cardItem.data,
                            text = cardItem.text,
                            isLike = cardItem.isLike
                        )
                    }
                }
            },
            child2 = {
                Column(
                    height = Constraint.Wrap
                ) {
                    Button(
                        text = "Предложить пост",
                        buttonType = ButtonType.Primary,
                        onClick = { routeToCreate() }
                    )
                }
            }
        )
    }
}

@Composable
fun Item(data: String, text: String, isLike: Boolean) {
    var _isLike: Boolean by remember { mutableStateOf(isLike) }
    Column(padding = Padding(top = 16)) {
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
                        icon = null, //MR.images.getImageByFileName("like"),
                        isClicked = _isLike,
                        onClick = { _isLike = !_isLike },
                        layoutModifier = LayoutModifier.padding(Padding(end = 8))
                    )
                    ImageButton(
                        text = "9",
                        icon = null, //MR.images.dislike,
                        isClicked = _isLike,
                        onClick = { _isLike = !_isLike },
                    )
                }
            }
        }
    }
}

fun mainApp(): NavigationRoot {
    return navigation {
        register("auth") { navigator ->
            Button("Login", ButtonType.Primary, onClick = {
                navigator.navigate("home")
            })
        }
        register("home", navigationTabs {
            register("tab1") {
                Text("tab1")
            }
            register("tab2", navigation {
                register("list") { navigator ->
                    PostsList(routeToCreate = { navigator.navigate("create") })
                }
                register("create") { navigator ->
                    CreatePost(onSuccess = { navigator.popBackStack() })
                }
            })
        })
    }
}

expect class NavigationRoot

interface Navigator {
    fun navigate(uri: String)
    fun popBackStack()
}

interface NavigationDsl {
    fun register(uri: String, screen: @Composable (Navigator) -> Unit)
    fun register(uri: String, navigationRoot: NavigationRoot)
}

expect fun navigation(block: NavigationDsl.() -> Unit): NavigationRoot
expect fun navigationTabs(block: NavigationDsl.() -> Unit): NavigationRoot
