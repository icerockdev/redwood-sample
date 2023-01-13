package ru.alex009.redwoodapp.android

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.Widget

class Stack: ru.alex009.redwood.schema.widget.Stack<@Composable () -> Unit> {
    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {

    }
    override val child1: Widget.Children<() -> Unit>
        get() = UiV
    override val child2: Widget.Children<() -> Unit>
        get() = TODO("Not yet implemented")
}