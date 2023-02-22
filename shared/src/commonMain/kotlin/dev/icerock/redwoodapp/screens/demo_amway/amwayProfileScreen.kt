package dev.icerock.redwoodapp.screens.demo_amway

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.ImageButton
import dev.icerock.redwood.schema.compose.Text
import org.example.library.MR

@Composable
fun AmwayProfileScreen() {
    Column {

        ImageButton(
            "".desc(),
            icon = MR.images.editor,
            onClick = {},
            layoutModifier = LayoutModifier.horizontalAlignment(CrossAxisAlignment.End)
        )

        Text(
            "Добрый день, <b>Эрика<\b>", textType = TextType.Primary,
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16))
        )
        Text(
            "example@icerockdev.com", textType = TextType.Primary,
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16)))
    }
}