package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.compose.Row
import ru.alex009.redwood.schema.compose.Text

@Composable
fun TabRow(
    onTabSelected: (String) -> Unit,
    currentScreen: String
) {
    Row(
        width = Constraint.Fill,
        height = Constraint.Wrap
    ) {
        Row(
            horizontalAlignment = MainAxisAlignment.Center
        ) {

        }
        Row(
            horizontalAlignment = MainAxisAlignment.Center
        ) {
           
        }
    }
}