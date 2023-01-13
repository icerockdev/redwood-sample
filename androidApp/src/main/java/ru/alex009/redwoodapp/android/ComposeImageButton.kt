package ru.alex009.redwoodapp.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import ru.alex009.redwood.schema.TextStyle
import ru.alex009.redwood.schema.widget.ImageButton

class ComposeImageButton: ImageButton<@Composable () -> Unit> {
    private var _text by mutableStateOf("")
    private var _icon: String? by mutableStateOf(null)
    private var _iconPadding: Int? by mutableStateOf(null)
    private var _textStyle: TextStyle? by mutableStateOf(null)

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {

    }

    override fun text(text: String) {
        _text = text
    }

    override fun icon(icon: String?) {
        _icon = icon
    }

    override fun textStyle(textStyle: TextStyle?) {
        _textStyle = textStyle
    }

    override fun iconPadding(iconPadding: Int?) {
        _iconPadding = iconPadding
    }
}