package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import ru.alex009.redwood.schema.ButtonType
import ru.alex009.redwood.schema.compose.Button
import ru.alex009.redwood.schema.compose.Card
import ru.alex009.redwood.schema.compose.Stack

@Composable
fun HelloWorld() {
        Column (overflow = Overflow.Scroll, width = Constraint.Fill){
            Card {
                Button(
                    layoutModifier = LayoutModifier.padding(Padding(10)),
                    text = "press me",
                    onClick = { "Hi!" },
                    buttonType = ButtonType.Action
                )
            }
            Stack(
                child1 = {
                    Button(
                        text = "press me 2",
                        onClick = { "Hi!" },
                        buttonType = ButtonType.Action
                    )
                },
                child2 = {
                    Button(
                        text = "press me 3",
                        onClick = { "Hi!" },
                        buttonType = ButtonType.Action
                    )
                }
            )
        }
}
