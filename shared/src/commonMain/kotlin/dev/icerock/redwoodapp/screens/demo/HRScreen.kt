package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.graphics.parseColor
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.Chip
import dev.icerock.redwood.schema.compose.Text
import org.example.library.MR

@Composable
fun HRScreen() {
    Column(height = Constraint.Fill) {
        Card(
            layoutModifier = LayoutModifier.padding(
                Padding(
                    top = 16,
                    start = 16,
                    end = 16
                )
            ),
            child = {
                Column() {
                    Text(
                        "<b>Положение</b> об использовании корпоративного транспорта",
                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                top = 8,
                                start = 16,
                                end = 16
                            )
                        ),
                        textType = TextType.Primary
                    )
                    Text(
                        "Запрос создан 17.02.23",
                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                top = 6,
                                start = 16,
                                end = 16
                            )
                        ),
                        textType = TextType.Secondary
                    )
                    Chip(
                        "Обрабатывается".desc(), icon = MR.images.checked_big,
                        textColor = Color.parseColor("#FF00A742"),
                        backgroundColor = Color.parseColor("#3300A742"),

                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                top = 10,
                                start = 16,
                                end = 16,
                                bottom = 8
                            )
                        )
                    )
                }
            }
        )
        Card(
            layoutModifier = LayoutModifier.padding(
                Padding(
                    top = 16,
                    start = 16,
                    end = 16
                )
            ),
            child = {
                Column() {
                    Text(
                        "<b>Инструкция</b> по технике безопасности в производственном цехе",
                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                top = 8,
                                start = 16,
                                end = 16
                            )
                        ),
                        textType = TextType.Primary
                    )
                    Text(
                        "Запрос создан 16.02.23",
                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                top = 6,
                                start = 16,
                                end = 16
                            )
                        ),
                        textType = TextType.Secondary
                    )
                    Chip(
                        "Отправлено на имэйл".desc(), icon = MR.images.check,
                        textColor = Color.parseColor("#FF7A767A"),
                        backgroundColor = Color.parseColor("#FFF2F2F2"),
                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                top = 10,
                                start = 16,
                                end = 16,
                                bottom = 8
                            )
                        )
                    )
                }
            }
        )
    }
}