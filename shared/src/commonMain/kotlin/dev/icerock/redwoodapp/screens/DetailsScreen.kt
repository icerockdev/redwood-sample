package dev.icerock.redwoodapp.screens

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.LONG_TEXT

@Composable
fun DetailsScreen(
    date: String,
    details: String,
) {
    Column(
        overflow = Overflow.Scroll
    ) {
        Text(date, layoutModifier = LayoutModifier.padding(Padding(16)), textType = TextType.Header)
        Text(details, layoutModifier = LayoutModifier.padding(Padding(horizontal = 16)))
        Text(LONG_TEXT, layoutModifier = LayoutModifier.padding(Padding(16)))
    }
}


