package ru.alex009.redwoodapp.android.widgets

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.compose.ComposeWidgetChildren
import ru.alex009.redwood.schema.widget.Card

class ComposeCard : Card<@Composable () -> Unit> {
    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        Card(
            backgroundColor = Color(0xFFF2F2F2),
            shape = RoundedCornerShape(8.dp),
            elevation = 0.dp
        ) {
            child.render()
        }
    }

    override val child = ComposeWidgetChildren()
}