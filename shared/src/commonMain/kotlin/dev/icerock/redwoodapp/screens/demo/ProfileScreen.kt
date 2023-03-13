package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Box
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.Divider
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.USER_AVATAR
import org.example.library.MR

@Composable
fun ProfileScreen(navigator: Navigator) {
    Box(
        background = MR.colors.unselected.color,
        child = {
            Column(
                height = Constraint.Fill,
                overflow = Overflow.Scroll,
                padding = Padding(start = 16, top = 24, end = 16, bottom = 24)
            ) {
                Card(
                    onClick = null,
                    child = {
                        Row(
                            verticalAlignment = CrossAxisAlignment.Center,
                            padding = Padding(16),
                            width = Constraint.Fill
                        ) {
                            Image(
                                width = Size.Const(50),
                                height = Size.Const(50),
                                url = USER_AVATAR,
                                placeholder = MR.images.tab_profile,
                                isCircleClip = true
                            )
                            Column(
                                padding = Padding(start = 8)
                            ) {
                                Text(
                                    text = "Иван иванович",
                                    textType = TextType.PrimaryBold
                                )
                                Text(
                                    text = "+ 7 900 000 00 00",
                                    textType = TextType.Secondary
                                )
                            }
                        }
                    }
                )
                Card(
                    layoutModifier = LayoutModifier.padding(Padding(top = 20)),
                    onClick = null,
                    child = {
                        Column {
                            Card(
                                onClick = {},
                                child = {
                                    Column(
                                        padding = Padding(
                                            horizontal = 16,
                                            vertical = 16
                                        ),
                                        width = Constraint.Fill
                                    ) {
                                        Text(
                                            text = "Сертификаты",
                                            textType = TextType.Primary
                                        )
                                    }
                                }
                            )
                            Divider(
                                isVertical = true,
                                layoutModifier = LayoutModifier.padding(Padding(start = 16))
                            )
                            Card(
                                onClick = {},
                                child = {
                                    Column(
                                        padding = Padding(
                                            horizontal = 16,
                                            vertical = 16
                                        ),
                                        width = Constraint.Fill
                                    ) {
                                        Text(
                                            text = "Абонементы",
                                            textType = TextType.Primary
                                        )
                                    }
                                }
                            )
                            Divider(
                                isVertical = true,
                                layoutModifier = LayoutModifier.padding(Padding(start = 16))
                            )
                            Card(
                                onClick = {},
                                child = {
                                    Column(
                                        padding = Padding(
                                            horizontal = 16,
                                            vertical = 16
                                        ),
                                        width = Constraint.Fill
                                    ) {
                                        Text(
                                            text = "История посещений",
                                            textType = TextType.Primary
                                        )
                                    }
                                }
                            )
                            Divider(
                                isVertical = true,
                                layoutModifier = LayoutModifier.padding(Padding(start = 16))
                            )
                            Card(
                                onClick = {},
                                child = {
                                    Column(
                                        padding = Padding(
                                            horizontal = 16,
                                            vertical = 16
                                        ),
                                        width = Constraint.Fill
                                    ) {
                                        Text(
                                            text = "Способы оплаты",
                                            textType = TextType.Primary
                                        )
                                    }
                                }
                            )
                            Divider(
                                isVertical = true,
                                layoutModifier = LayoutModifier.padding(Padding(start = 16))
                            )
                            Card(
                                onClick = {},
                                child = {
                                    Column(
                                        padding = Padding(
                                            horizontal = 16,
                                            vertical = 16
                                        ),
                                        width = Constraint.Fill
                                    ) {
                                        Text(
                                            text = "Поддержка",
                                            textType = TextType.Primary
                                        )
                                    }
                                }
                            )
                        }
                    }
                )
                Card(
                    layoutModifier = LayoutModifier.padding(Padding(top = 20)),
                    onClick = {},
                    child = {
                        Column(
                            padding = Padding(
                                horizontal = 16,
                                vertical = 16
                            ),
                            width = Constraint.Fill
                        ) {
                            Text(
                                text = "Выйти из приложения",
                                textType = TextType.PrimaryRed
                            )
                        }
                    }
                )
            }
        }
    )
}
