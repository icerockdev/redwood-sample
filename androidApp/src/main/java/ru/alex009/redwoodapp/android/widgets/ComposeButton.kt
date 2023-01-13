package ru.alex009.redwoodapp.android.widgets

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.getColor
import ru.alex009.redwood.schema.TextStyle
import ru.alex009.redwood.schema.widget.Button

class ComposeButton : Button<@Composable () -> Unit> {
    private var _text by mutableStateOf("")
    private var _background: ColorResource? by mutableStateOf(null)
    private var _borderColor: ColorResource? by mutableStateOf(null)
    private var _cornerRadius: Int? by mutableStateOf(null)
    private var _textStyle: TextStyle? by mutableStateOf(null)
    private var _onClick: () -> Unit by mutableStateOf({})

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        val context: Context = LocalContext.current
        val bgColor: dev.icerock.moko.graphics.Color? = _background?.getColor(context)

        Button(
            modifier = bgColor?.let { Modifier.background(color = Color(it.rgba)) } ?: Modifier,
            onClick = _onClick,
            shape = RoundedCornerShape(_cornerRadius?.dp ?: 0.dp)
        ) {
            Text(text = _text)
        }
    }

    override fun text(text: String) {
        _text = text
    }

    override fun background(background: ColorResource?) {
        _background = background
    }

    override fun borderColor(borderColor: ColorResource?) {
        _borderColor = borderColor
    }

    override fun cornerRadius(cornerRadius: Int?) {
        _cornerRadius = cornerRadius
    }

    override fun textStyle(textStyle: TextStyle?) {
        _textStyle = textStyle
    }

    override fun onClick(onClick: (() -> Unit)?) {
        _onClick = onClick ?: {}
    }
}