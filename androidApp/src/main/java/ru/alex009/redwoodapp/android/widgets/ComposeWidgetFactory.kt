package ru.alex009.redwoodapp.android.widgets

import androidx.compose.runtime.Composable
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory

object ComposeWidgetFactory : RedwoodAppSchemaWidgetFactory<@Composable () -> Unit> {
    override fun TextInput() = ComposeTextInput()

    override fun Text() = ComposeText()

    override fun Image() = ComposeImage()

    override fun Card() = ComposeCard()

    override fun Stack() = ComposeStack()

    override fun ImageButton() = ComposeImageButton()

    override fun Button() = ComposeButton()

    override fun TabItem() = ComposeTabItem()
}
