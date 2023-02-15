package dev.icerock.redwoodapp.android.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.widget.Text
import dev.icerock.redwoodapp.android.theme.TextStyles
import dev.icerock.redwoodapp.android.types.BoldText
import dev.icerock.redwoodapp.android.types.HeaderText
import dev.icerock.redwoodapp.android.types.PrimaryText
import dev.icerock.redwoodapp.android.types.SecondaryText

class ComposeText : Text<@Composable () -> Unit> {
    private var _textState: String by mutableStateOf("")
    private var _isSingleLine: Boolean by mutableStateOf(false)
    private var _textType: TextType by mutableStateOf(TextType.Primary)

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        when (_textType) {
            TextType.Primary -> PrimaryText(
                text = _textState,
                isSingleLine = _isSingleLine
            )
            TextType.Secondary -> SecondaryText(
                text = _textState,
                isSingleLine = _isSingleLine
            )
            TextType.Header -> HeaderText(
                text = _textState,
                isSingleLine = _isSingleLine
            )
            TextType.Bold -> BoldText(
                text = _textState,
                isSingleLine = _isSingleLine
            )
            TextType.H2 -> PrimaryText(
                text = _textState,
                isSingleLine = _isSingleLine,
                textStyle = TextStyles.header3
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

    override fun width(width: Size) {
        //
    }
}
