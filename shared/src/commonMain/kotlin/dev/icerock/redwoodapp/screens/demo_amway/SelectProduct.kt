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
import dev.icerock.redwood.schema.compose.Divider
import dev.icerock.redwood.schema.compose.ImageButton
import dev.icerock.redwood.schema.compose.ListItem
import dev.icerock.redwood.schema.compose.RowWithWeight
import dev.icerock.redwood.schema.compose.Stack
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwood.schema.compose.TextInput
import dev.icerock.redwoodapp.ext.weight
import org.example.library.MR

@Composable
fun SelectProduct(nextRoute: () -> Unit) {

    var fio: String by remember { mutableStateOf("") }
    var isPolicyClicked by remember { mutableStateOf(false) }
    Box(
        background = MR.colors.white.color,
        child = {
            Column(
                overflow = Overflow.Scroll
            ) {
                Text(
                    text = "Выберите марафон",
                    textType = TextType.Header,
                    layoutModifier = LayoutModifier.padding(Padding(top = 24, start = 16, end = 16))
                )
                TextInput(
                    state = fio,
                    hint = "Change to searh widget".desc(),
                    onChange = {
                        fio = it
                    },
                    inputType = InputType.Text,
                    layoutModifier = LayoutModifier.padding(Padding(16))
                )
                ListItem(
                    title = "Избавься от лишнего за 3 недели".desc(),
                    subtitle = "81971".desc(),
                    onClick = { nextRoute()},
                ){}
                Divider(layoutModifier = LayoutModifier.padding(Padding(start = 16)))

                ListItem(
                    title = "Сотри следы долгих праздников изнутри".desc(),
                    subtitle = "81916".desc(),
                    onClick = { nextRoute()},
                ){}
                Divider(layoutModifier = LayoutModifier.padding(Padding(start = 16)))


            }
        }
    )
}