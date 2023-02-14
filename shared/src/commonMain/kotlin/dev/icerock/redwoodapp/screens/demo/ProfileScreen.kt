package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Divider
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.ListItem
import dev.icerock.redwood.schema.compose.Stack
import dev.icerock.redwood.schema.compose.Switch
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
                ListItem(
                    layoutModifier = LayoutModifier.padding(padding = Padding(top = 16)),
                    title = "Электронная почта".desc(),
                    subtitle = "example@gmail.com".desc(),
                    onClick = null,
                    child = {},
                    icon = MR.images.profile
                )
                Divider()
                var switchActive by remember { mutableStateOf(false) }
                
                ListItem(
                    title = "Получать уведомления".desc(),
                    subtitle = "На электронную почту".desc(),
                    onClick = {switchActive = switchActive.not()},
                    icon = MR.images.settings
                ) {
                    Switch(isActive = switchActive,
                        isEnabled = true,
                        onChangeState = { switchActive = it })
                }
                Divider()
                ListItem(
                    title = "Получать уведомления".desc(),
                    subtitle = "В приложении".desc(),
                    onClick = null,
                    icon = MR.images.settings
                ) {
                    Switch(isActive = false,
                        isEnabled = false,
                        onChangeState = { switchActive = it })
                }
                Divider()
                ListItem(
                    title = "Выход".desc(),
                    subtitle = null,
                    onClick = {
                              navigator.navigate(Screens.LOGIN)
                    },
                    icon = MR.images.radio,
                    tintColor = Color(0xFFC02828)
                ) {
                }
                Divider()
            }
        }
    }
}