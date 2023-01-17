package ru.alex009.redwoodapp.android.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import ru.alex009.redwood.schema.ButtonType
import ru.alex009.redwood.schema.widget.Button
import ru.alex009.redwoodapp.android.types.ActionButton
import ru.alex009.redwoodapp.android.types.PrimaryButton
import ru.alex009.redwoodapp.android.types.SecondaryButton

class ComposeButton : Button<@Composable () -> Unit> {
    private var _text by mutableStateOf("")
    private var _buttonType by mutableStateOf(ButtonType.Primary)
    private var _onClick: () -> Unit by mutableStateOf({})
    private var _enabled by mutableStateOf(true)

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        when(_buttonType) {
            ButtonType.Primary -> PrimaryButton(text = _text, enabled = _enabled, onClick = _onClick)
            ButtonType.Secondary -> SecondaryButton(text = _text, enabled = _enabled, onClick = _onClick)
            ButtonType.Action -> ActionButton(text = _text, enabled = _enabled, onClick = _onClick)
        }
    }

    override fun text(text: String) {
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
