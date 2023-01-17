package ru.alex009.redwoodapp.android.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import com.skydoves.landscapist.coil.CoilImage
import ru.alex009.redwood.schema.widget.Image

class ComposeImage : Image<@Composable () -> Unit> {
    private var _urlState by mutableStateOf("")
    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        CoilImage(imageModel = _urlState)
    }

    override fun url(url: String) {
        _urlState = url
    }
}