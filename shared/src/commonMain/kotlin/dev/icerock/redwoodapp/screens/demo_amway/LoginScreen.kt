package dev.icerock.redwoodapp.screens.demo_amway

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.InputType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Space
import dev.icerock.redwood.schema.compose.Tabs
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwood.schema.compose.TextInput
import dev.icerock.redwoodapp.Box
import org.example.library.MR

@Composable
fun LoginScreen(nextScreen: () -> Unit) {
    Box {

        Column (horizontalAlignment = CrossAxisAlignment.Center){

            Image(width = Size.Const(120),
            height = Size.Const(50),
            url = null,
            placeholder = MR.images.amway_logo,
            layoutModifier = LayoutModifier.padding(Padding(bottom = 30)))
            Card(
                layoutModifier = LayoutModifier
                    .padding(Padding(horizontal = 16)),
                onClick = null,
                child = {
                    var phoneNumber by remember { mutableStateOf("") }
                    var isNewUser by remember { mutableStateOf(true) }
                    Column() {
                        Tabs(
                            texts = listOf("Авторизация".desc(), "Новый аккаунт".desc()),
                            onChange = { isNewUser = it == 0 },
                            selectedTab = if (isNewUser) 0 else 1,
                            layoutModifier = LayoutModifier.padding(Padding(start = 16, end = 16, top = 16))
                        )
                        TextInput(
                            state = phoneNumber,
                            hint = "Электронная почта".desc(),
                            onChange = {
                                phoneNumber = it
                            },
                            inputType = InputType.Text,
                            layoutModifier = LayoutModifier.padding(Padding(24))
                        )
                        if (isNewUser) {
                            var password by remember { mutableStateOf("") }
                            TextInput(
                                state = password,
                                hint = "Пароль".desc(),
                                onChange = {
                                    password = it
                                },
                                inputType = InputType.Password,
                                layoutModifier = LayoutModifier.padding(Padding(horizontal = 24))
                            )
                            Text(
                                text = "Восстановить пароль",
                                textType = TextType.Link,
                                layoutModifier = LayoutModifier.padding(Padding(24))

                            )
                        } else {
                            var password by remember { mutableStateOf("") }
                            var passwordRepeat by remember { mutableStateOf("") }
                            TextInput(
                                state = password,
                                hint = "Придумайте пароль".desc(),
                                onChange = {
                                    password = it
                                },
                                inputType = InputType.Password,
                                layoutModifier = LayoutModifier.padding(Padding(horizontal = 24))
                            )
                            TextInput(
                                state = passwordRepeat,
                                hint = "Повторите пароль".desc(),
                                onChange = {
                                    passwordRepeat = it
                                },
                                inputType = InputType.Password,
                                layoutModifier = LayoutModifier.padding(Padding(24))
                            )
                        }
                        Button(
                            text = "Войти".desc(),
                            buttonType = ButtonType.Primary,
                            width = Size.Fill,
                            onClick = {
                                //запрос на бэк с номером
                                nextScreen()
                            },
                            layoutModifier = LayoutModifier.padding(Padding(start = 24, end = 24,
                            bottom = 24))
                        )
                    }
                })
            Space(background = MR.colors.error_color.color,
            width = 0,
            height = 80)
        }
    }
}