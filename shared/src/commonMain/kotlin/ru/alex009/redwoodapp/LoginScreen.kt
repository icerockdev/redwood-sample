package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import ru.alex009.redwood.schema.ButtonType
import ru.alex009.redwood.schema.compose.Button
import ru.alex009.redwood.schema.compose.TextInput
import ru.alex009.redwoodapp.navigation.Navigator

@Composable
fun LoginScreen(navigator: Navigator) {
    Box {
        Column {
            var login: String by remember { mutableStateOf("") }
            TextInput(login, "login", layoutModifier = LayoutModifier.padding(Padding(16)),
                onChange = {
                    login = it
                }
            )
            Button(
                "Login", buttonType = ButtonType.Primary,
                enabled = login.isNotEmpty() ,
                onClick = {
                    navigator.navigate("tabs")
                }, layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 100))
            )
        }
    }
}