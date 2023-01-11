package ru.alex009.redwoodapp.android

import androidx.compose.runtime.Composable
import ru.alex009.redwood.schema.widget.Card
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory

object ComposeUiWidgetFactory : RedwoodAppSchemaWidgetFactory<@Composable () -> Unit> {
    override fun TextInput() = ComposeTextInputWidget()

    override fun Text() = ComposeTextWidget()

    override fun Image() = ComposeImageWidget()
    override fun Card(): Card<@Composable () -> Unit> = ComposeCardWidget()
}