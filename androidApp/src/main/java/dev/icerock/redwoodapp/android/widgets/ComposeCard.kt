package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.compose.ComposeWidgetChildren
import dev.icerock.redwood.schema.widget.Card

class ComposeCard : Card<@Composable () -> Unit> {
    override var layoutModifiers: LayoutModifier = LayoutModifier
    private var _onClick: (() -> Unit)? by mutableStateOf(null)

    override val value = @Composable {
        Card(
            modifier = Modifier.let {
                val onCLick = _onClick
                if (onCLick != null) {
                    it.clickable {
                        onCLick()
                    }
                } else {
                    it
                }
            },
            backgroundColor = Color.White,
            shape = RoundedCornerShape(8.dp),
            elevation = 0.dp
        ) {
            child.render()
        }
    }

    override val child = ComposeWidgetChildren()
    override fun onClick(onClick: (() -> Unit)?) {
        _onClick = onClick
    }
}