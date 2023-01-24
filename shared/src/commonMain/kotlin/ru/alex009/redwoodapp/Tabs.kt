package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.compose.Row
import ru.alex009.redwood.schema.compose.TabItem
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
            TabItem(text = "Tab 1", icon = null, isClicked = currentScreen == "tab1", onClick = {
                onTabSelected("tab1")
            })
        }
        Row(
            horizontalAlignment = MainAxisAlignment.Center
        ) {
            TabItem(text = "Tab 2", icon = null, isClicked = currentScreen == "tab2", onClick = {
                onTabSelected("tab2")
            })
        }
    }
}