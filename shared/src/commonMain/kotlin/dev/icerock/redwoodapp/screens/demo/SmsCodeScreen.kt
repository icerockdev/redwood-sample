package dev.icerock.redwoodapp.screens.demo

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
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.InputType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.ImageButton
import dev.icerock.redwood.schema.compose.Stack
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwood.schema.compose.TextInput
import dev.icerock.redwoodapp.Box
import dev.icerock.redwoodapp.screens.demo.navigation.Screens
import org.example.library.MR

@Composable
fun SmsCodeScreen(
    navigator: Navigator
) {
    Stack(
        child1 = {
            Column(
                padding = Padding(start = 8, top = 16)
            ) {
                ImageButton(
                    text = "".desc(),
                    icon = MR.images.cross,
                    onClick = {
                        navigator.navigate(Screens.PHONE_NUMBER_LOGIN)
                    }
                )
            }
        },
        child2 = {
            Box {
                Card(
                    layoutModifier = LayoutModifier.padding(Padding(horizontal = 16)),
                    onClick = {},
                    child = {
                        var smsCode by remember { mutableStateOf("") }
                        Column(padding = Padding(24)) {
                            Text(
                                text = "Подтверждение номера",
                                textType = TextType.H2,
                                layoutModifier = LayoutModifier.horizontalAlignment(
                                    CrossAxisAlignment.Center
                                )
                            )
                            Text(
                                text = "На номер +7 (000) 000 00 00 отправлен СМС-код",
                                textType = TextType.Secondary,
                                layoutModifier = LayoutModifier
                                    .padding(Padding(top = 16))
                                    .horizontalAlignment(CrossAxisAlignment.Center)
                            )
                            TextInput(
                                state = smsCode,
                                hint = "Формат кода: 9186".desc(),
                                onChange = {
                                    smsCode = it
                                    if (it.length == 4) {
                                        navigator.navigate(Screens.FIRST_INFO)
                                    }
                                },
                                inputType = InputType.Text,
                                layoutModifier = LayoutModifier.padding(Padding(top = 32))
                            )
                            Button(
                                text = "Запросить код повторно".desc(),
                                buttonType = ButtonType.Text,
                                width = Size.Fill,
                                onClick = {
                                    //запрос на бэк с номером
                                },
                                layoutModifier = LayoutModifier.padding(Padding(top = 32))
                            )
                        }
                    })
            }
        }
    )
}
