package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Divider
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwood.schema.compose.TextInput
import org.example.library.MR

@Composable
fun CatalogScreen() {
    Column(
        height = Constraint.Wrap,
        width = Constraint.Wrap
    ) {
        Column(
            padding = Padding(start = 16, top = 16, end = 16)
        ) {
            var search by remember { mutableStateOf("") }
            Row {
                Image(
                    placeholder = MR.images.location,
                    url = "",
                    width = Size.Const(20),
                    height = Size.Const(20)
                )
                Text(
                    text = "Москва",
                    textType = TextType.Link,
                    layoutModifier = LayoutModifier.padding(Padding(start = 8))
                )
            }
            TextInput(
                state = search,
                hint = "Здесь будет поиск".desc(),
                layoutModifier = LayoutModifier.padding(Padding(top = 16))
            )

        }
        Divider(
            layoutModifier = LayoutModifier.padding(Padding(top = 20))
        )
        Column(
            overflow = Overflow.Scroll
        ) {
            Row(
                overflow = Overflow.Scroll
            ) {

            }
        }
    }
}