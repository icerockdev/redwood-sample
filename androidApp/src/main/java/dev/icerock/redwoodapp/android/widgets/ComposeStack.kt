package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.compose.ComposeWidgetChildren

class ComposeStack: dev.icerock.redwood.schema.widget.Stack<@Composable () -> Unit> {
    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        Box(modifier = Modifier.fillMaxSize()) {
           child1.render()
            Column(modifier = Modifier.align(Alignment.BottomEnd)) {
                child2.render()
            }
        }
    }
    override val child1 = ComposeWidgetChildren()
    override val child2 = ComposeWidgetChildren()
}