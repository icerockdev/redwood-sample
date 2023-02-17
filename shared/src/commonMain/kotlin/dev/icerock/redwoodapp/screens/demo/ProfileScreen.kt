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
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Divider
import dev.icerock.redwood.schema.compose.ListItem
import dev.icerock.redwood.schema.compose.Switch
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.screens.demo.navigation.Screens

@Composable
fun ProfileScreen(navigator: Navigator) {

    Column(
        horizontalAlignment = CrossAxisAlignment.Start,
        verticalAlignment = MainAxisAlignment.Start,
        height = Constraint.Fill
    ) {
        Text(
            "Здравствуйте, \n" +
                    "Григорий",
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16,
            vertical = 24)),
            textType = TextType.Header
        )
        ListItem(
            title = "Электронная почта".desc(),
            subtitle = "example@gmail.com".desc(),
            onClick = null,
            child = {},
            icon = null
        )
        Divider()
        var switchActive by remember { mutableStateOf(false) }

        ListItem(
            title = "Получать уведомления".desc(),
            subtitle = "На электронную почту".desc(),
            onClick = { switchActive = switchActive.not() },
            icon = null
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
            icon = null
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
                navigator.navigate(Screens.PROFILE)
            },
            icon = null, //MR.images.exit,
            tintColor = Color(0xFFC02828)
        ) {
        }
        Divider()
    }
}
