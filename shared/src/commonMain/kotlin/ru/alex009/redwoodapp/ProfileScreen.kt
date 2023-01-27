package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import ru.alex009.redwood.schema.ButtonType
import ru.alex009.redwood.schema.TextType
import ru.alex009.redwood.schema.compose.Button
import ru.alex009.redwood.schema.compose.Image
import ru.alex009.redwood.schema.compose.Stack
import ru.alex009.redwood.schema.compose.Text
import org.example.library.MR

@Composable
fun ProfileScreen() {
    Stack(
        child1 = {
            Row(
                verticalAlignment = CrossAxisAlignment.Start,
                horizontalAlignment = MainAxisAlignment.Center,
                width = Constraint.Fill
            ) {
                Column(
                    horizontalAlignment = CrossAxisAlignment.Center,
                    verticalAlignment = MainAxisAlignment.Start,
                    height = Constraint.Fill
                ) {
                    Image(
                        width = 120,
                        height = 120,
                        url = "https://www.dierennieuws.nl/wp-content/uploads/2022/01/kat-in-sneeuw-800x445.jpg",
                        placeholder = MR.images.ava_preview,
                        layoutModifier = LayoutModifier.padding(Padding(top = 120))
                    )
                    Text(
                        "Иванов Петр",
                        layoutModifier = LayoutModifier.padding(Padding(top = 12)),
                        textType = TextType.Header
                    )
                }
            }
        },
        child2 = { Button("Выход", buttonType = ButtonType.Secondary) }
    )
}