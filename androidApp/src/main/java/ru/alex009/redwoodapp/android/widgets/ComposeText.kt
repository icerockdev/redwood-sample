package ru.alex009.redwoodapp.android.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import ru.alex009.redwood.schema.TextType
import ru.alex009.redwood.schema.widget.Text
import ru.alex009.redwoodapp.android.types.PrimaryText
import ru.alex009.redwoodapp.android.types.SecondaryText

class ComposeText : Text<@Composable () -> Unit> {
    private var _textState: String by mutableStateOf("")
    private var _isSingleLine: Boolean by mutableStateOf(false)
    private var _textType: TextType by mutableStateOf(TextType.Primary)

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        when(_textType) {
            TextType.Primary -> PrimaryText(
                text = _textState,
                isSingleLine = _isSingleLine
            )
            TextType.Secondary -> SecondaryText(
                text = _textState,
                isSingleLine = _isSingleLine
            )
        }
    }

    override fun text(text: String) {
        _textState = text
    }

    override fun isSingleLine(isSingleLine: Boolean) {
        _isSingleLine = isSingleLine
    }

    override fun textType(textType: TextType?) {
        _textType = textType ?: TextType.Primary
    }
}
