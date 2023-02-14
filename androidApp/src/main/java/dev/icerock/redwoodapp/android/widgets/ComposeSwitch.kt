package dev.icerock.redwoodapp.android.widgets

import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import dev.icerock.redwood.schema.widget.Switch
import dev.icerock.redwoodapp.android.theme.Colors

class ComposeSwitch : Switch<@Composable () -> Unit> {

    private var _isEnabled: Boolean by mutableStateOf(true)
    private var _isActive: Boolean by mutableStateOf(false)
    private var _onStateChange: (Boolean) -> Unit by mutableStateOf({})

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        Switch(
            checked = _isActive,
            enabled = _isEnabled,
            onCheckedChange = _onStateChange,
            colors  = SwitchDefaults.colors(
                checkedThumbColor = Colors.primary,
                checkedTrackColor = Colors.primary,
            ),
        )
    }

    override fun isActive(isActive: Boolean) {
        _isActive = isActive
    }

    override fun onChangeState(onChangeState: ((Boolean) -> Unit)?) {
        _onStateChange = onChangeState ?: {}
    }

    override fun isEnabled(isEnabled: Boolean) {
        _isEnabled = isEnabled
    }
}