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
import dev.icerock.redwoodapp.android.types.TextWithStyle

class ComposeText : Text<@Composable () -> Unit> {
    private var _textState: String by mutableStateOf("")
    private var _isSingleLine: Boolean by mutableStateOf(false)
    private var _textType: TextType by mutableStateOf(TextType.Primary)

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        when (_textType) {
            TextType.Header -> TextWithStyle(
                text = _textState,
                isSingleLine = _isSingleLine,
                textStyle = TextStyles.h1
            )
            TextType.H2 -> TextWithStyle(
                text = _textState,
                isSingleLine = _isSingleLine,
                textStyle = TextStyles.h2
            )
            TextType.Primary -> TextWithStyle(
                text = _textState,
                isSingleLine = _isSingleLine,
                textStyle = TextStyles.primary
            )
            TextType.PrimaryBold -> TextWithStyle(
                text = _textState,
                isSingleLine = _isSingleLine,
                textStyle = TextStyles.primaryBold
            )
            TextType.PrimarySmall -> TextWithStyle(
                text = _textState,
                isSingleLine = _isSingleLine,
                textStyle = TextStyles.primarySmall
            )
            TextType.Secondary -> TextWithStyle(
                text = _textState,
                isSingleLine = _isSingleLine,
                textStyle = TextStyles.secondary
            )
            TextType.SecondarySmall -> TextWithStyle(
                text = _textState,
                isSingleLine = _isSingleLine,
                textStyle = TextStyles.secondarySmall
            )
            TextType.Caption -> TextWithStyle(
                text = _textState,
                isSingleLine = _isSingleLine,
                textStyle = TextStyles.caption
            )
            TextType.Link -> TextWithStyle(
                text = _textState,
                isSingleLine = _isSingleLine,
                textStyle = TextStyles.link
            )
            TextType.PrimaryRed -> TextWithStyle(
                text = _textState,
                isSingleLine = _isSingleLine,
                textStyle = TextStyles.primaryRed
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
