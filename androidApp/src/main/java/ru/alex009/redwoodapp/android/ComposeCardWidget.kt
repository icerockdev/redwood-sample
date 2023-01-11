package ru.alex009.redwoodapp.android

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.Widget
import ru.alex009.redwood.schema.widget.Card

class ComposeCardWidget : Card<@Composable () -> Unit> {
    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value: () -> Unit
        get() = TODO("Not yet implemented")
    override val children: Widget.Children<() -> Unit>
        get() = TODO("Not yet implemented")
}