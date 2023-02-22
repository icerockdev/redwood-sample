package dev.icerock.redwoodapp.screens.demo_amway

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.InputType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Box
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Chip
import dev.icerock.redwood.schema.compose.ImageButton
import dev.icerock.redwood.schema.compose.RowWithWeight
import dev.icerock.redwood.schema.compose.Stack
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwood.schema.compose.TextInput
import dev.icerock.redwoodapp.ext.weight
import org.example.library.MR

@Composable
fun TargetScreen(nextRoute: () -> Unit) {

    var fio: String by remember { mutableStateOf("") }
    var isPolicyClicked by remember { mutableStateOf(false) }
    Box(
        background = MR.colors.white.color,
        child = {
            Stack(
                child1 = {
                    Column(
                        overflow = Overflow.Scroll,
                        padding = Padding(start = 16, top = 24, end = 16)
                    ) {
                        Text(
                            text = "Еще чуть-чуть",
                            textType = TextType.Primary
                        )
                        Text(
                            text = "Поставьте цель на марафон",
                            textType = TextType.Header,
                            layoutModifier = LayoutModifier.padding(Padding(top = 8))
                        )
                        Text(
                            text = "Укажите вес тела, которого хотите достичь",
                            textType = TextType.Primary,
                            layoutModifier = LayoutModifier.padding(Padding(top = 24))
                        )


                        TextInput(
                            state = fio,
                            hint = "Например, 60 кг".desc(),
                            onChange = {
                                fio = it
                            },
                            inputType = InputType.Text,
                            layoutModifier = LayoutModifier.padding(Padding(top = 16))
                        )
                    }
                },
                child2 = {
                    Column(
                        horizontalAlignment = CrossAxisAlignment.End,
                        padding = Padding(start = 16, top = 8, end = 16, bottom = 24)
                    ) {
                        Button(
                            text = "Далее".desc(),
                            buttonType = ButtonType.Primary,
                            width = Size.Fill,
                            onClick = {
                                nextRoute()
                            },
                            layoutModifier = LayoutModifier.padding(Padding(top = 16))
                        )
                    }
                }
            )
        }
    )
}