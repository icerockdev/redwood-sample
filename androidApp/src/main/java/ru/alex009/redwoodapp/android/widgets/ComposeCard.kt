package ru.alex009.redwoodapp.android.widgets

import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.compose.ComposeWidgetChildren
import dev.icerock.moko.resources.ColorResource
import ru.alex009.redwood.schema.widget.Card

class ComposeCard : Card<@Composable () -> Unit> {
    private var _background: ColorResource? by mutableStateOf(null)
    private var _cornerRadius: Int? by mutableStateOf(null)

    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        Card {
            child.render()
        }
    }

    override val child = ComposeWidgetChildren()

    override fun background(background: ColorResource?) {
        _background = background
    }

    override fun cornerRadius(cornerRadius: Int?) {
        _cornerRadius = cornerRadius
    }
}