package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.compose.Tabs
import dev.icerock.redwood.schema.compose.Text

@Composable
fun ServiceScreen() {
    Column(
        height = Constraint.Fill,
        width = Constraint.Fill,
        padding = Padding(start = 16, top = 24, end = 16)
    ) {
        var selectedTab by remember { mutableStateOf(0) }
        var title by remember { mutableStateOf("Активные заказы") }
        Tabs(
            texts = listOf(
                "Активные".desc(),
                "Архив".desc()
            ),
            onChange ={
                    selectedTab = it
                    title = if(it == 0) "Активные заказы" else
                        "Заказы в архиве"
                },
            selectedTab = selectedTab
        )

        Text(
            text = title,
            layoutModifier = LayoutModifier.padding(Padding(top = 16))
        )
    }
}