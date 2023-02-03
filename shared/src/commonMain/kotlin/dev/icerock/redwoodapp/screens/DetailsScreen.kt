package dev.icerock.redwoodapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.LONG_TEXT
import dev.icerock.redwoodapp.ToolabrArgs
import dev.icerock.redwoodapp.navigation.Navigator
import dev.icerock.redwoodapp.navigation.ScreenSettings

@Composable
fun DetailsScreen(
    navigator: Navigator,
    date: String,
    details: String,
    screenSettings: ScreenSettings<ToolabrArgs>
) {
    Column(
        overflow = Overflow.Scroll
    ) {
        Text(date, layoutModifier = LayoutModifier.padding(Padding(16)), textType = TextType.Header)
        Text(details, layoutModifier = LayoutModifier.padding(Padding(horizontal = 16)))
        Text(LONG_TEXT, layoutModifier = LayoutModifier.padding(Padding(16)))
    }
}


