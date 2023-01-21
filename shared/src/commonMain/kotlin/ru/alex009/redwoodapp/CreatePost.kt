package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import ru.alex009.redwood.schema.ButtonType
import ru.alex009.redwood.schema.TextType
import ru.alex009.redwood.schema.compose.Button
import ru.alex009.redwood.schema.compose.Text
import ru.alex009.redwood.schema.compose.TextInput

@Composable
fun CreatePost(onSuccess: () -> Unit) {
    Column(
        height = Constraint.Fill,
        width = Constraint.Fill,
        padding = Padding(16),
        verticalAlignment = MainAxisAlignment.Start
    ) {
        var text: String by remember { mutableStateOf("") }
        Column(
            height = Constraint.Wrap,
            width = Constraint.Fill,
            verticalAlignment = MainAxisAlignment.Start
        ) {
            TextInput(
                state = text,
                hint = "Напишите, что вы знаете интересного? Полностью анонимно\n",
                onChange = { text = it },
                layoutModifier = LayoutModifier.padding(Padding(bottom = 8))
            )
            Text(
                text = "0/2000",
                isSingleLine = true,
                textType = TextType.Secondary,
                layoutModifier = LayoutModifier
                    .horizontalAlignment(CrossAxisAlignment.End)
                    .padding(Padding(horizontal = 16))
            )
        }
        Column(
            height = Constraint.Wrap,
            width = Constraint.Fill,
            verticalAlignment = MainAxisAlignment.End
        ) {
            Button(
                text = "Отправить",
                buttonType = ButtonType.Primary,
                onClick = { onSuccess() }
            )
        }
    }
}