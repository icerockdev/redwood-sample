package ru.alex009.redwoodapp.android

import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.redwood.LayoutModifier
import ru.alex009.redwood.schema.widget.Text

class ComposeTextWidget : Text<@Composable () -> Unit> {
    private var textState by mutableStateOf("")
    private var _textSize by mutableStateOf(14)
    private var _color by mutableStateOf(0xFF000000)

    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        Text(
            text = textState,
            fontSize = _textSize.sp,
            color = Color(_color)
        )
    }

    override fun text(text: String) {
        textState = text
    }

    override fun textSize(textSize: Int) {
        _textSize = textSize
    }

    override fun color(color: Long) {
        _color = color
    }
}