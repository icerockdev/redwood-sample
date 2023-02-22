package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.InputType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Chip
import dev.icerock.redwood.schema.compose.FooterColumn
import dev.icerock.redwood.schema.compose.ImageButton
import dev.icerock.redwood.schema.compose.RowWithWeight
import dev.icerock.redwood.schema.compose.Space
import dev.icerock.redwood.schema.compose.Stack
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwood.schema.compose.TextInput
import dev.icerock.redwoodapp.Box
import dev.icerock.redwoodapp.ext.weight
import org.example.library.MR

@Composable
fun FirstInfoScreen(
    toMainScreen: () -> Unit
) {
    var fio: String by remember { mutableStateOf("") }
    var email: String by remember { mutableStateOf("") }
    var isPolicyClicked by remember { mutableStateOf(false) }
    FooterColumn(
        child = {
            Column(
                overflow = Overflow.Scroll,
                padding = Padding(start = 16, top = 24, end = 16)
            ) {
                Text(
                    text = "Регистрация прошла успешно",
                    textType = TextType.Primary
                )
                Text(
                    text = "Заполните немного данных о себе",
                    textType = TextType.Header,
                    layoutModifier = LayoutModifier.padding(Padding(top = 8))
                )
                Text(
                    text = "ФИО",
                    textType = TextType.Primary,
                    layoutModifier = LayoutModifier.padding(Padding(top = 24))
                )
                TextInput(
                    state = fio,
                    hint = "Иванов Иван Иванович".desc(),
                    onChange = {
                        fio = it
                    },
                    inputType = InputType.Text,
                    layoutModifier = LayoutModifier.padding(Padding(top = 16))
                )
                Text(
                    text = "Ваш пол",
                    textType = TextType.Primary,
                    layoutModifier = LayoutModifier.padding(Padding(top = 24))
                )
                var gender: Int? by remember { mutableStateOf(null) }
                Row(
                    padding = Padding(top = 16)
                ) {
                    Chip(
                        text = "Мужской".desc(),
                        onClick = { gender = 0 },
                        icon = null,
                        border = if(gender == 0) null else 1,
                        backgroundColor = (if (gender == 0) MR.colors.primary else MR.colors.unselected).color,
                        textColor = (if (gender == 0) MR.colors.white else MR.colors.black).color,
                    )
                    Chip(
                        text = "Женский".desc(),
                        onClick = { gender = 1 },
                        icon = null,
                        border = if(gender == 1) null else 1,
                        backgroundColor = (if (gender == 1) MR.colors.primary else MR.colors.unselected).color,
                        textColor = (if (gender == 1) MR.colors.white else MR.colors.black).color,
                        layoutModifier = LayoutModifier.padding(Padding(start = 8))
                    )
                }
                Text(
                    text = "Электронная почта",
                    textType = TextType.Primary,
                    layoutModifier = LayoutModifier.padding(Padding(top = 16))
                )
                Text(
                    text = "Вместо звонков отправим письмо с выгодными предложениями",
                    textType = TextType.Secondary,
                    layoutModifier = LayoutModifier.padding(Padding(top = 8))
                )
                TextInput(
                    state = email,
                    hint = "example@icerockdev.com".desc(),
                    onChange = {
                        email = it
                    },
                    inputType = InputType.Text,
                    layoutModifier = LayoutModifier.padding(Padding(top = 16))
                )
                Space(MR.colors.white.color,
                0, 16)
            }
        },
        footer = {
                Column(
                    horizontalAlignment = CrossAxisAlignment.End,
                    padding = Padding(start = 16, top = 8, end = 16, bottom = 24)
                ) {
                    RowWithWeight {
                        ImageButton(
                            text = "".desc(),
                            icon = if (isPolicyClicked) MR.images.check_box_clicked else MR.images.check_box_unclicked,
                            onClick = {
                                isPolicyClicked = !isPolicyClicked
                            }
                        )
                        Text(
                            text = "Ознакомлен(-а) с Регламентом о защите и обработке персональных данных",
                            textType = TextType.SecondarySmall,
                            layoutModifier = LayoutModifier.weight(1f)
                        )
                    }
                    Button(
                        text = "В приложение".desc(),
                        buttonType = ButtonType.Primary,
                        width = Size.Fill,
                        onClick = {
                            toMainScreen()
                        },
                        layoutModifier = LayoutModifier.padding(Padding(top = 16))
                    )
                }
        }
    )
}