package ru.alex009.redwoodapp.android.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import ru.alex009.redwood.schema.TextStyle
import ru.alex009.redwood.schema.widget.Text

class ComposeTextWidget : Text<@Composable () -> Unit> {
    private var _textState: String by mutableStateOf("")
    private var _isSingleLine: Boolean by mutableStateOf(false)
    private var _textStyle: TextStyle? by mutableStateOf(null)

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        Text(
            text = _textState,
            maxLines = if (_isSingleLine) 1 else Int.MAX_VALUE,
//            color = _textStyle?.textColor
        )
    }

    override fun text(text: String) {
        _textState = text
    }

    override fun isSingleLine(isSingleLine: Boolean) {
        _isSingleLine = isSingleLine
    }

    override fun textStyle(textStyle: TextStyle?) {
        _textStyle = textStyle
    }
}
