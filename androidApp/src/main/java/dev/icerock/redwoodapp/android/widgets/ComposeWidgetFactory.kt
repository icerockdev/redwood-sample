package dev.icerock.redwoodapp.android.widgets

import androidx.compose.runtime.Composable
import dev.icerock.redwood.schema.widget.RedwoodAppSchemaWidgetFactory

object ComposeWidgetFactory : RedwoodAppSchemaWidgetFactory<@Composable () -> Unit> {
    override fun TextInput() = ComposeTextInput()

    override fun Text() = ComposeText()

    override fun Image() = ComposeImage()

    override fun Card() = ComposeCard()

    override fun Stack() = ComposeStack()

    override fun ImageButton() = ComposeImageButton()

    override fun Button() = ComposeButton()

    override fun Space() = ComposeSpace()
}
