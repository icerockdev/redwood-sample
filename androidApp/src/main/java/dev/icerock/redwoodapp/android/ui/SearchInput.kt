package dev.icerock.redwoodapp.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.redwood.schema.InputType
import dev.icerock.redwoodapp.android.R
import dev.icerock.redwoodapp.android.theme.Colors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchInput(
    inputField: CMutableStateFlow<String>,
    placeholder: StringDesc
) {
    val colors = TextFieldDefaults.textFieldColors(
        cursorColor = Color.Black,
        textColor = Colors.black,
        backgroundColor = Colors.gray60
    )
    val _textState by inputField.collectAsState()

    BasicTextField(
        modifier = Modifier.fillMaxWidth()
            .clip(CircleShape)
            .background(Colors.gray60),
        value = _textState,
        onValueChange = {
            inputField.value = it
        },
        visualTransformation = VisualTransformation.None,
        singleLine = true,
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = _textState,
                innerTextField = innerTextField,
                placeholder = {
                    Text(
                        text = placeholder.toString(LocalContext.current),
                        color = Colors.gray70
                    )
                },
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                leadingIcon = {
                    Image(
                        modifier = Modifier.size(32.dp),
                        contentScale = ContentScale.Inside,
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        colorFilter = ColorFilter.tint(Colors.black),
                        contentDescription = ""
                    )
                },
                colors = colors,
                contentPadding = PaddingValues(0.dp)
            )
        }
    )
}