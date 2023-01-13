package ru.alex009.redwoodapp.android

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import ru.alex009.redwood.schema.TextStyle
import ru.alex009.redwood.schema.widget.TextInput

class ComposeTextInputWidget : TextInput<@Composable () -> Unit> {
    private var _textState by mutableStateOf("")
    private var _hintState by mutableStateOf("")
    private var _onChangeState: (String) -> Unit by mutableStateOf({})
    private var _textStyle: TextStyle? by mutableStateOf(null)

    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        TextField(
            value = _textState,
            onValueChange = _onChangeState,
            label = { Text(_hintState) }
        )
    }

    override fun state(state: String) {
        _textState = state
    }

    override fun hint(hint: String) {
        _hintState = hint
    }

    override fun onChange(onChange: ((String) -> Unit)?) {
        _onChangeState = onChange ?: {}
    }

    override fun textStyle(textStyle: TextStyle?) {
        _textStyle = textStyle
    }
}