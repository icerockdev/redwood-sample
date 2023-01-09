package ru.alex009.redwoodapp.android

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import ru.alex009.redwood.schema.widget.TextInput

class ComposeTextInputWidget : TextInput<@Composable () -> Unit> {
    private var textState by mutableStateOf("")
    private var hintState by mutableStateOf("")
    private var onChangeState: (String) -> Unit by mutableStateOf({})

    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        TextField(
            value = textState,
            onValueChange = onChangeState,
            label = { Text(hintState) }
        )
    }

    override fun state(state: String) {
        textState = state
    }

    override fun hint(hint: String) {
        hintState = hint
    }

    override fun onChange(onChange: ((String) -> Unit)?) {
        onChangeState = onChange ?: {}
    }
}