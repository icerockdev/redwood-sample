package ru.alex009.redwoodapp.android.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import ru.alex009.redwood.schema.widget.Space
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier

class ComposeSpace : Space<@Composable () -> Unit> {
    private var _background: dev.icerock.moko.graphics.Color by mutableStateOf(
        dev.icerock.moko.graphics.Color(
            0x00000000
        )
    )
    private var _width: Int by mutableStateOf(-1)
    private var _height: Int by mutableStateOf(-1)

    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        Spacer(
            modifier = when {
                _height == -1 && _width == -1 -> Modifier
                    .fillMaxSize()
                    .background(Color(_background.rgba))
                _height == -1 -> Modifier
                    .fillMaxHeight()
                    .width(_width.dp)
                    .background(Color(_background.rgba))
                _width == -1 -> Modifier
                    .fillMaxWidth()
                    .height(_height.dp)
                    .background(Color(_background.rgba))
                else -> Modifier
                    .height(_height.dp)
                    .width(_width.dp)
                    .background(Color(_background.rgba))
            }
        )
    }

    override fun background(background: dev.icerock.moko.graphics.Color) {
        _background = background
    }

    override fun width(width: Int) {
        _width = width
    }

    override fun height(height: Int) {
        _height = height
    }
}
