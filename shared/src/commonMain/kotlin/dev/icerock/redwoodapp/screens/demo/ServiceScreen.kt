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
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Tabs
import org.example.library.MR

@Composable
fun ServiceScreen() {
    Column(
        height = Constraint.Fill,
        width = Constraint.Fill,
        padding = Padding(top = 16)
    ) {
        val active = listOf(
            MR.images.Order,
            MR.images.Order2
        )

        val arch = listOf(
            MR.images.Order
        )

        var selectedTab by remember { mutableStateOf(0) }
        var list by remember { mutableStateOf(active) }
        Tabs(
            texts = listOf(
                "Активные".desc(),
                "Архив".desc()
            ),
            onChange ={
                    selectedTab = it
                    list = if(it == 0) active else arch
                },
            selectedTab = selectedTab
        )

        Column(
            padding = Padding(start = 16, end = 16, top = 8),
            overflow = Overflow.Scroll,
            width = Constraint.Fill
        ) {
            list.forEach { item ->
                Image(
                    width = Size.Fill,
                    height = Size.Const(166),
                    url = "",
                    placeholder = item,
                    layoutModifier = LayoutModifier.padding(Padding(top = 16)),
                )
            }
        }
    }
}

