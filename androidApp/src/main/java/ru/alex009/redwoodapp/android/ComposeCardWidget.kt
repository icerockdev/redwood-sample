package ru.alex009.redwoodapp.android

import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.ViewGroupChildren
import app.cash.redwood.widget.Widget
import app.cash.redwood.widget.compose.ComposeWidgetChildren
import dev.icerock.moko.resources.ColorResource
import ru.alex009.redwood.schema.widget.Card

class ComposeCardWidget : Card<@Composable () -> Unit> {
    //private var _child: Widget.Children<() -> Unit> by mutableStateOf()
    private var _background: ColorResource? by mutableStateOf(null)
    private var _cornerRadius: Int? by mutableStateOf(null)

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {

        child.render()
    }

    override val child = ComposeWidgetChildren()

    override fun background(background: ColorResource?) {
        _background = background
    }

    override fun cornerRadius(cornerRadius: Int?) {
        _cornerRadius = cornerRadius
    }
}