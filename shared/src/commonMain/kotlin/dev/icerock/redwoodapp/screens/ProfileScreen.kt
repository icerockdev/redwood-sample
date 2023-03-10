package dev.icerock.redwoodapp.screens

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
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Stack
import dev.icerock.redwood.schema.compose.Text
import org.example.library.MR
import dev.icerock.redwoodapp.USER_AVATAR
import dev.icerock.redwoodapp.USR_NAME
import dev.icerock.redwoodapp.navigation.Navigator

@Composable
fun ProfileScreen(navigator: Navigator) {
    Stack(
        child1 = {
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
                }
            }
        },
        child2 = {
            Column(
                height = Constraint.Wrap,
                horizontalAlignment = CrossAxisAlignment.Center,
                width = Constraint.Fill
            ) {
                Button(
                    MR.strings.profile_logout.desc(),
                    buttonType = ButtonType.Secondary,
                    onClick = {
                        navigator.navigate("login")
                    },
                    layoutModifier = LayoutModifier.padding(
                        Padding(
                            horizontal = 16,
                            vertical = 16
                        )
                    )
                )
            }
        }
    )
}