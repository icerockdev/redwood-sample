package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.Padding
import app.cash.redwood.widget.compose.ComposeWidgetChildren
import dev.icerock.redwood.schema.widget.RowWithWeight

class ComposeRowWithWeight : RowWithWeight<@Composable () -> Unit> {
    override val childs = ComposeWidgetChildren()
    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        Row {

            childs.widgets.forEach {
                var padding: Padding? = null
                it.layoutModifiers.forEach { element ->
                    (element as? Padding)?.let {
                        padding = it
                    }
                }
                Box(modifier = Modifier.weight(1f).let {
                    val padding = padding?.padding
                    if (padding != null) {
                        it.padding(
                            start = padding.start.dp,
                            end = padding.end.dp,
                            top = padding.top.dp,
                            bottom = padding.bottom.dp
                        )
                    } else {
                        it
                    }
                }) {
                    it.value.invoke()

                }
            }
        }
    }


}