package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.resources.desc.desc
import ru.alex009.redwood.schema.TextType
import ru.alex009.redwood.schema.compose.Text
import ru.alex009.redwoodapp.navigation.Navigator
import ru.alex009.redwoodapp.navigation.ScreenSettings

@Composable
fun DetailsScreen(
    navigator: Navigator,
    date: String,
    details: String,
    screenSettings: ScreenSettings
) {
    LaunchedEffect(screenSettings){
        screenSettings.setTitle(date.desc())
    }
    Column(
        overflow = Overflow.Scroll
    ) {
        Text(date, layoutModifier = LayoutModifier.padding(Padding(16)), textType = TextType.Header)
        Text(details, layoutModifier = LayoutModifier.padding(Padding(horizontal = 16)))
        Text(LONG_TEXT, layoutModifier = LayoutModifier.padding(Padding(16)))
    }
}


