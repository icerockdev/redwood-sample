package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.navigation.viewmodel.getViewModel
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.InputType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwood.schema.compose.TextInput
import dev.icerock.redwoodapp.Box
import dev.icerock.redwoodapp.SimpleLoginViewModel
import dev.icerock.redwoodapp.screens.demo.navigation.Screens
import org.example.library.MR

@Composable
fun LoginScreen(
    navigator: Navigator,
) {
    val viewModel: SimpleLoginViewModel = getViewModel {
        SimpleLoginViewModel()
    }

    val buttonText: StringDesc by viewModel.tetxFlow.collectAsState()

    Box {
        Card(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16)),
            child = {
                Column(
                    horizontalAlignment = CrossAxisAlignment.Start,
                    verticalAlignment = MainAxisAlignment.Center
                ) {
                    var login: String by remember { mutableStateOf("123") }
                    var password: String by remember { mutableStateOf("123") }
                    Text(
                        "Вход в аккаунт",
                        textType = TextType.Header,
                        layoutModifier = LayoutModifier.padding(Padding(24)),
                        )
                    TextInput(login,
                        MR.strings.auth_login.desc(),
                        layoutModifier = LayoutModifier.padding(Padding(horizontal = 24, vertical = 12)),
                        onChange = {
                            login = it
                        })
                    TextInput(
                        password,
                        MR.strings.auth_password.desc(),
                        layoutModifier = LayoutModifier.padding(Padding(horizontal = 24, vertical = 12)),
                        onChange = {
                            password = it
                        },
                        inputType = InputType.Password
                    )
                    Button(
                        "Войти".desc(),
                        width = Size.Fill,
                        buttonType = ButtonType.Primary,
                        enabled = login.isNotEmpty() && password.isNotEmpty(),
                        onClick = {
                            navigator.navigate(Screens.MAIN)
                        },
                        layoutModifier = LayoutModifier.padding(
                            Padding(24)
                        )
                    )
                }
            })
    }
}
