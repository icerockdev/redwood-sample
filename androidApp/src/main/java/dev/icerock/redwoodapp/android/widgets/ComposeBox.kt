package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.compose.ComposeWidgetChildren
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.graphics.colorInt
import dev.icerock.redwood.schema.widget.Box

class ComposeBox : Box<@Composable () -> Unit> {
    var background by mutableStateOf<Color?>(null)
    override val child = ComposeWidgetChildren()

    override fun background(background: Color) {
        this.background = background
    }

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
       Box(modifier = Modifier
           .fillMaxSize()
           .background(
               background?.let {
                   androidx.compose.ui.graphics.Color(it.colorInt())
               } ?: androidx.compose.ui.graphics.Color.Transparent
           )){
           child.render()
       }
    }

}
