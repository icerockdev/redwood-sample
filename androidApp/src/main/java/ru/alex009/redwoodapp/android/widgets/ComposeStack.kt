package ru.alex009.redwoodapp.android.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.Widget
import app.cash.redwood.widget.compose.ComposeWidgetChildren

class ComposeStack: ru.alex009.redwood.schema.widget.Stack<@Composable () -> Unit> {
    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        Box {
           child1.render()
           child2.render()
        }
    }
    override val child1 = ComposeWidgetChildren()
    override val child2 = ComposeWidgetChildren()
}