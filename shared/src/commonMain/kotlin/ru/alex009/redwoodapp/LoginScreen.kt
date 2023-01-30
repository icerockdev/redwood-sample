package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import ru.alex009.redwood.schema.ButtonType
import ru.alex009.redwood.schema.InputType
import ru.alex009.redwood.schema.compose.Button
import ru.alex009.redwood.schema.compose.Image
import ru.alex009.redwood.schema.compose.TextInput
import ru.alex009.redwoodapp.navigation.Navigator
import org.example.library.MR
@Composable
fun LoginScreen(navigator: Navigator) {
    Box {
        Column(horizontalAlignment = CrossAxisAlignment.Center, verticalAlignment = MainAxisAlignment.Center) {
            var login: String by remember { mutableStateOf("") }
            var password: String by remember { mutableStateOf("") }
            Image(
                120,
                120,
                placeholder = MR.images.ava_preview,
                layoutModifier = LayoutModifier.padding(Padding(bottom = 100)),
                url = null
            )
            TextInput(login, "Login", layoutModifier = LayoutModifier.padding(Padding(16)),
                onChange = {
                    login = it
                }
            )
            TextInput(
                password,
                "Password",
                layoutModifier = LayoutModifier.padding(Padding(horizontal = 16)),
                onChange = {
                    password = it
                },
                inputType = InputType.Password
            )
            Button(
                "Login", buttonType = ButtonType.Primary,
                enabled = login.isNotEmpty() && password.isNotEmpty(),
                onClick = {
                    navigator.navigate("tabs")
                }, layoutModifier = LayoutModifier.padding(Padding(start = 16, top = 100, end = 16))
            )
        }
    }
}