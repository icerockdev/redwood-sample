package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import ru.alex009.redwood.schema.compose.Button
import ru.alex009.redwood.schema.compose.Text

@Composable
fun HelloWorld() {
    Column(
        padding = Padding(16),
        width = Constraint.Fill,
        height = Constraint.Fill,
        horizontalAlignment = CrossAxisAlignment.Center
    ) {
        var counter: Int by remember { mutableStateOf(0) }

        Text(text = counter.toString())

        Row(
            width = Constraint.Fill,
            horizontalAlignment = MainAxisAlignment.SpaceBetween
        ) {
            Button(
                text = "+",
                onClick = { counter++ }
            )
            Button(
                text = "-",
                onClick = { counter-- }
            )
        }
    }
}
