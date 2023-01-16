package ru.alex009.redwoodapp.android.widgets

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.compose.Row
import com.skydoves.landscapist.coil.CoilImage
import ru.alex009.redwood.schema.widget.ImageButton

class ComposeImageButton : ImageButton<@Composable () -> Unit> {
    private var _text by mutableStateOf("")
    private var _icon: String? by mutableStateOf(null)
    private var _onClick: () -> Unit by mutableStateOf({})

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        Button(onClick = _onClick) {
            Row {
                CoilImage(imageModel = _icon)
                Text(text = _text)
            }
        }
    }

    override fun text(text: String) {
        _text = text
    }

    override fun icon(icon: String?) {
        _icon = icon
    }

    override fun onClick(onClick: (() -> Unit)?) {
        _onClick = onClick ?: {}
    }
}