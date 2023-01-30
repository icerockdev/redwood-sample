package dev.icerock.redwoodapp.android.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import app.cash.redwood.LayoutModifier
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.widget.Button
import dev.icerock.redwoodapp.android.types.ActionButton
import dev.icerock.redwoodapp.android.types.PrimaryButton
import dev.icerock.redwoodapp.android.types.SecondaryButton

class ComposeButton : Button<@Composable () -> Unit> {
    private var _text: StringDesc by mutableStateOf("".desc())
    private var _buttonType by mutableStateOf(ButtonType.Primary)
    private var _onClick: () -> Unit by mutableStateOf({})
    private var _enabled by mutableStateOf(true)

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        when (_buttonType) {
            ButtonType.Primary -> PrimaryButton(
                text = _text.toString(LocalContext.current),
                enabled = _enabled,
                onClick = _onClick
            )
            ButtonType.Secondary -> SecondaryButton(
                text = _text.toString(LocalContext.current),
                enabled = _enabled,
                onClick = _onClick
            )
            ButtonType.Action -> ActionButton(
                text = _text.toString(LocalContext.current),
                enabled = _enabled,
                onClick = _onClick
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
}
