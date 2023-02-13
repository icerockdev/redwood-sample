package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Stack
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.Box
import org.example.library.MR
import dev.icerock.redwoodapp.USER_AVATAR
import dev.icerock.redwoodapp.USR_NAME
import dev.icerock.redwoodapp.navigation.Navigator
import dev.icerock.redwoodapp.screens.demo.navigation.Screens

@Composable
fun ProfileScreen(navigator: Navigator) {
    Box {
        Row(
            verticalAlignment = CrossAxisAlignment.Start,
            horizontalAlignment = MainAxisAlignment.Center,
            width = Constraint.Fill
        ) {
            Column(
                horizontalAlignment = CrossAxisAlignment.Center,
                verticalAlignment = MainAxisAlignment.Start,
                height = Constraint.Fill
            ) {
                Image(
                    width = 120,
                    height = 120,
                    url = USER_AVATAR,
                    placeholder = MR.images.ava_preview,
                    layoutModifier = LayoutModifier.padding(Padding(top = 120))
                )
                Text(
                    USR_NAME,
                    layoutModifier = LayoutModifier.padding(Padding(top = 12)),
                    textType = TextType.Header
                )

                Button(
                    "Настройки уведмлений".desc(),
                    width = Size.Fill,
                    buttonType = ButtonType.Secondary,
                    onClick = {

                    },
                    layoutModifier = LayoutModifier.padding(
                        Padding(
                            start = 16,
                            bottom = 16,
                            end = 8,
                            top = 50
                        )
                    )
                )
                Button(
                    "Итория трекинга".desc(),
                    width = Size.Fill,
                    buttonType = ButtonType.Secondary,
                    onClick = {

                    },
                    layoutModifier = LayoutModifier.padding(
                        Padding(
                            start = 16,
                            bottom = 16,
                            end = 8
                        )
                    )
                )
                Button(
                    MR.strings.profile_logout.desc(),
                    width = Size.Fill,
                    buttonType = ButtonType.Secondary,
                    onClick = {
                        navigator.navigate(Screens.LOGIN)
                    },
                    layoutModifier = LayoutModifier.padding(
                        Padding(
                            start = 16,
                            bottom = 16,
                            end = 16
                        )
                    )
                )
            }
        }
    }
}