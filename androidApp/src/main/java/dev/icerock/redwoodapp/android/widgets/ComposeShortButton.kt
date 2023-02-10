package dev.icerock.redwoodapp.android.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import app.cash.redwood.LayoutModifier
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.widget.Button
import dev.icerock.redwood.schema.widget.ShortButton
import dev.icerock.redwoodapp.android.types.ActionButton
import dev.icerock.redwoodapp.android.types.PrimaryButton
import dev.icerock.redwoodapp.android.types.SecondaryButton
import dev.icerock.redwoodapp.android.types.TonnalButton

class ComposeShortButton : ShortButton<@Composable () -> Unit> {
    private var _text: StringDesc by mutableStateOf("".desc())
    private var _buttonType by mutableStateOf(ButtonType.Primary)
    private var _onClick: () -> Unit by mutableStateOf({})
    private var _enabled by mutableStateOf(true)
    private var _icon by mutableStateOf<ImageResource?>(null)

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        when (_buttonType) {
            ButtonType.Primary -> PrimaryButton(
                text = _text.toString(LocalContext.current),
                enabled = _enabled,
                onClick = _onClick,
                icon = _icon
            )
            ButtonType.Secondary -> SecondaryButton(
                text = _text.toString(LocalContext.current),
                enabled = _enabled,
                onClick = _onClick,
                icon = _icon

            )
            ButtonType.Text -> ActionButton(
                text = _text.toString(LocalContext.current),
                enabled = _enabled,
                onClick = _onClick,
                icon = _icon
            )
            ButtonType.Tonal -> TonnalButton(
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
}
