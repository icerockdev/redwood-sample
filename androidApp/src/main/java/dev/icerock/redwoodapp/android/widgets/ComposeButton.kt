package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.widget.Button
import dev.icerock.redwoodapp.android.types.ActionButton
import dev.icerock.redwoodapp.android.types.PrimaryButton
import dev.icerock.redwoodapp.android.types.SecondaryButton
import dev.icerock.redwoodapp.android.types.TonnalButton

class ComposeButton : Button<@Composable () -> Unit> {
    private var _text: StringDesc by mutableStateOf("".desc())
    private var _buttonType by mutableStateOf(ButtonType.Primary)
    private var _onClick: () -> Unit by mutableStateOf({})
    private var _enabled by mutableStateOf(true)
    private var _icon by mutableStateOf<ImageResource?>(null)
    private var _width by mutableStateOf<Size>(Size.Wrap)

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        when (_buttonType) {
            ButtonType.Primary -> PrimaryButton(
                modifier = Modifier.width(_width),
                text = _text.toString(LocalContext.current),
                enabled = _enabled,
                onClick = _onClick,
                icon = _icon
            )
            ButtonType.Secondary -> SecondaryButton(
                modifier = Modifier.width(_width),
                text = _text.toString(LocalContext.current),
                enabled = _enabled,
                onClick = _onClick,
                icon = _icon
            )
            ButtonType.Text -> ActionButton(
                modifier = Modifier.width(_width),
                text = _text.toString(LocalContext.current),
                enabled = _enabled,
                onClick = _onClick,
                icon = _icon
            )
            ButtonType.Tonal -> TonnalButton(
                modifier = Modifier.width(_width),
                text = _text.toString(LocalContext.current),
                enabled = _enabled,
                onClick = _onClick,
                icon = _icon
            )
        }
    }

    override fun text(text: StringDesc) {
        _text = text
    }

    override fun onClick(onClick: (() -> Unit)?) {
        _onClick = onClick ?: {}
    }

    override fun buttonType(buttonType: ButtonType) {
        _buttonType = buttonType
    }

    override fun enabled(enabled: Boolean) {
        _enabled = enabled
    }

    override fun icon(icon: ImageResource?) {
        _icon = icon
    }

    override fun width(width: Size) {
        _width = width
    }
}


fun Modifier.width(width: Size?): Modifier = this.let {
    when (width) {
        is Size.Wrap -> it
        is Size.Fill -> it.fillMaxWidth()
        is Size.Const -> it.width(width.value.dp)
        else -> it
    }
}

fun Modifier.height(height: Size?): Modifier = this.let {
    when (height) {
        is Size.Wrap -> it
        is Size.Fill -> it.fillMaxHeight()
        is Size.Const -> it.height(height.value.dp)
        else -> it
    }
}

fun Modifier.aspectRatio(ratio: Float?): Modifier = this.let {
    return@let if (ratio == null) {
        it
    } else {
        it.aspectRatio(ratio)
    }
}