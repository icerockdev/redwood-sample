package ru.alex009.redwoodapp.android.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import ru.alex009.redwood.schema.widget.Image

class ComposeImage : Image<@Composable () -> Unit> {
    private var urlState by mutableStateOf("")
    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        Text(urlState)
    }

    override fun url(url: String) {
        urlState = url
    }
}