package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import com.skydoves.landscapist.coil.CoilImage
import dev.icerock.moko.resources.ImageResource
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.widget.Image

class ComposeImage : Image<@Composable () -> Unit> {
    private var _urlState: String? by mutableStateOf("")
    private var _width: Size? by mutableStateOf(null)
    private var _height: Size? by mutableStateOf(null)
    private var _placeholder: Int? by mutableStateOf(null)
    private var _aspectRatio: Float? by mutableStateOf(null)
    private var _isCircleClip: Boolean by mutableStateOf(false)

    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        CoilImage(
            modifier = Modifier
                .width(_width)
                .height(_height)
                .aspectRatio(_aspectRatio)
                .let {
                    if (_isCircleClip) {
                        it.clip(CircleShape)
                    } else {
                        it
                    }
                },
            imageModel = _urlState,
            placeHolder = _placeholder?.let { painterResource(it) },
            error = _placeholder?.let { painterResource(it) }
        )
    }

    override fun width(width: Size?) {
        _width = width
    }

    override fun height(height: Size?) {
        _height = height
    }

    override fun url(url: String?) {
        _urlState = url
    }

    override fun placeholder(placeholder: ImageResource?) {
        _placeholder = placeholder?.drawableResId
    }

    override fun aspectRatio(aspectRatio: Float?) {
        _aspectRatio = aspectRatio
    }

    override fun isCircleClip(isCircleClip: Boolean) {
        _isCircleClip = isCircleClip
    }
}
