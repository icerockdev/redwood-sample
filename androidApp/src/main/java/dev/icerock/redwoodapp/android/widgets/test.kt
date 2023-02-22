package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.InputType
import dev.icerock.redwoodapp.android.R
import dev.icerock.redwoodapp.android.theme.Colors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun test(
    _state: String,
    _hint: StringDesc,
    _onChange: (String) -> Unit,
    _showMic: Boolean,
    _onMicClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Colors.searchBackground)
            .padding(horizontal = 2.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(horizontal = 8.dp)
            /*.size(24.dp)*/,
            painter = painterResource(id = R.drawable.search),
            contentDescription = ""
        )
        val colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Colors.gray90,
            backgroundColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent
        )

        BasicTextField(
            modifier = Modifier.weight(1f),
            value = _state,
            onValueChange = _onChange,
            singleLine = true,
            decorationBox = { innerTextField ->
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = _state,
                    innerTextField = innerTextField,
                    placeholder = {
                        Text(
                            text = _hint?.toString(LocalContext.current) ?: "",
                            color = Colors.gray90
                        )
                    },
                    enabled = true,
                    singleLine = false,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember { MutableInteractionSource() },
                    contentPadding = PaddingValues(top = 2.dp, bottom = 4.dp),
                    border = {
                        TextFieldDefaults.BorderBox(
                            enabled = true,
                            isError = false,
                            interactionSource = remember { MutableInteractionSource() },
                            colors = colors
                        )
                    }
                )
            }
        )

        if (_showMic) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { _onMicClick() }
                    .padding(horizontal = 8.dp),
                painter = painterResource(id = R.drawable.microphone),
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
fun preview() {
    Column(
        Modifier
            .background(Color.White)
            .fillMaxHeight()
            .padding(16.dp)) {
        test(
            _state = "",
            _hint = "Салон, название услуги ".desc(),
            _onChange = { },
            _showMic = true
        ) {

        }
    }
}

