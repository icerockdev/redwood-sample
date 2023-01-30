package ru.alex009.redwoodapp.android.widgets

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import ru.alex009.redwood.schema.InputType
import ru.alex009.redwood.schema.widget.TextInput

class ComposeTextInput : TextInput<@Composable () -> Unit> {
    private var _textState by mutableStateOf("")
    private var _hintState: StringDesc by mutableStateOf("".desc())
    private var _onChangeState: (String) -> Unit by mutableStateOf({})
    private var _inputType: InputType? by mutableStateOf(InputType.Text)

    override var layoutModifiers: LayoutModifier = LayoutModifier

    @OptIn(ExperimentalMaterialApi::class)
    override val value = @Composable {
        val colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            focusedBorderColor = Color(0xFF0C7BC7),
            unfocusedBorderColor = Color(0x2828282E),
            disabledBorderColor = Color(0xFF0C7BC7)
        )

        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = _textState,
            onValueChange = _onChangeState,
            visualTransformation = if (_inputType == InputType.Password) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            singleLine = true,
            decorationBox = { innerTextField ->
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = _textState,
                    innerTextField = innerTextField,
                    placeholder = {
                        Text(
                            text = _hintState.toString(LocalContext.current),
                            color = Color(0xFFA9A9A9)
                        )
                    },
                    enabled = true,
                    singleLine = false,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember { MutableInteractionSource() },
                    border = {
                        TextFieldDefaults.BorderBox(
                            enabled = true,
                            isError = false,
                            interactionSource = remember { MutableInteractionSource() },
                            colors = colors,
                            shape = RoundedCornerShape(8.dp)
                        )
                    }
                )
            }
        )
    }

    override fun state(state: String) {
        _textState = state
    }

    override fun hint(hint: StringDesc) {
        _hintState = hint
    }

    override fun onChange(onChange: ((String) -> Unit)?) {
        _onChangeState = onChange ?: {}
    }

    override fun inputType(inputType: InputType?) {
        _inputType = inputType
    }
}