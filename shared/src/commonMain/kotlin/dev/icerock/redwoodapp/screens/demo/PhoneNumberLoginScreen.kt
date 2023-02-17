package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.InputType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwood.schema.compose.TextInput
import dev.icerock.redwoodapp.Box

@Composable
fun PhoneNumberLoginScreen(sendPhoneClick: () -> Unit) {
    Box {
        Card(
            layoutModifier = LayoutModifier
                .padding(Padding(horizontal = 16)),
            onClick = {},
            child = {
                var phoneNumber by remember { mutableStateOf("") }
                Column(padding = Padding(24)) {
                    Text(
                        text = "Введите номер \nтелефона",
                        textType = TextType.H2
                    )
                    Text(
                        text = "Чтобы войти в приложение или зарегистрироваться в нем",
                        textType = TextType.Secondary,
                        layoutModifier = LayoutModifier.padding(Padding(top = 16))
                    )
                    TextInput(
                        state = phoneNumber,
                        hint = "Номер телефона".desc(),
                        onChange = {
                            phoneNumber = it
                        },
                        inputType = InputType.Text,
                        layoutModifier = LayoutModifier.padding(Padding(top = 32))
                    )
                    Button(
                        text = "Отправить код".desc(),
                        buttonType = ButtonType.Primary,
                        width = Size.Fill,
                        onClick = {
                            //запрос на бэк с номером
                            sendPhoneClick()
                        },
                        layoutModifier = LayoutModifier.padding(Padding(top = 32))
                    )
                }
        })
    }
}