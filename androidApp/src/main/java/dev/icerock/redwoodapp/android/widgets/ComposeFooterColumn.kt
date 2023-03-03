package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.Widget
import app.cash.redwood.widget.compose.ComposeWidgetChildren
import dev.icerock.redwood.schema.widget.HeaderFooterContent

class ComposeFooterColumn : HeaderFooterContent<@Composable () -> Unit> {
    override val child = ComposeWidgetChildren()
    override val footer = ComposeWidgetChildren()
    override val header = ComposeWidgetChildren()
    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                header.render()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                child.render()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                footer.render()
            }
        }
    }
}