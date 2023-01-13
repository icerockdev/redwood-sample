package ru.alex009.redwoodapp.android

import androidx.compose.runtime.Composable
import ru.alex009.redwood.schema.widget.Button
import ru.alex009.redwood.schema.widget.Card
import ru.alex009.redwood.schema.widget.ImageButton
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory
import ru.alex009.redwood.schema.widget.Stack

object ComposeUiWidgetFactory : RedwoodAppSchemaWidgetFactory<@Composable () -> Unit> {
    override fun TextInput() = ComposeTextInputWidget()

    override fun Text() = ComposeTextWidget()

    override fun Image() = ComposeImageWidget()
    override fun Card(): Card<@Composable () -> Unit> = ComposeCardWidget()
    override fun Stack(): Stack<() -> Unit> {
        TODO("Not yet implemented")
    }

    override fun ImageButton(): ImageButton<() -> Unit> {
        TODO("Not yet implemented")
    }

    override fun Button(): Button<() -> Unit> {
        TODO("Not yet implemented")
    }
}