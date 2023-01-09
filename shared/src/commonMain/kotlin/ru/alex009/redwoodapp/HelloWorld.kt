package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import ru.alex009.redwood.schema.compose.Text
import ru.alex009.redwood.schema.compose.TextInput

@Composable
fun HelloWorld() {
    Column(padding = Padding(16)) {
        var text: String by remember { mutableStateOf("some text") }

        TextInput(
            state = text,
            hint = "super text",
            onChange = { text = it },
            layoutModifier = LayoutModifier.horizontalAlignment(CrossAxisAlignment.Center)
        )

        Row {
            Text(text = "left $text")
            Text(text = "right $text")
        }
    }
}
