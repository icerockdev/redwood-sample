package ru.alex009.redwoodapp.android.widgets

import androidx.compose.runtime.Composable
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory

object ComposeWidgetFactory : RedwoodAppSchemaWidgetFactory<@Composable () -> Unit> {
    override fun Text() = ComposeText()

    override fun Button() = ComposeButton()
}
