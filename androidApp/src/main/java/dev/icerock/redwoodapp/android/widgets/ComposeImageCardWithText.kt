package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import com.skydoves.landscapist.coil.CoilImage
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.graphics.colorInt
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.widget.ImageCardWithText

class ComposeImageCardWithText : ImageCardWithText<@Composable () -> Unit> {

    private var _text: StringDesc by mutableStateOf("".desc())
    private var _textBackgroundColor: Color? by mutableStateOf(null)
    private var _textColor: Color? by mutableStateOf(null)
    private var _onClick: (() -> Unit)? by mutableStateOf(null)
    private var _url by mutableStateOf("")
    private var _placeholder: Int? by mutableStateOf(null)
    private var _width: Size? by mutableStateOf(null)
    private var _height: Size? by mutableStateOf(null)

    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                _onClick?.let { it() }
            }
        ) {
            CoilImage(
                modifier = Modifier
                    .width(_width)
                    .height(_height),
                imageModel = _url,
                placeHolder = _placeholder?.let { painterResource(it) },
                error = _placeholder?.let { painterResource(it) }
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(_textBackgroundColor?.let { androidx.compose.ui.graphics.Color(it.colorInt()) } ?: androidx.compose.ui.graphics.Color.Transparent)
                    .padding(vertical = 8.dp)
                    .align(Alignment.BottomEnd),
                text = _text.toString(LocalContext.current),
                textAlign = TextAlign.Center,
                color = _textColor?.let { androidx.compose.ui.graphics.Color(it.colorInt()) } ?: androidx.compose.ui.graphics.Color.Black
            )
        }
    }

    override fun width(width: Size?) {
        _width = width
    }

    override fun height(height: Size?) {
        _height = height
    }

    override fun text(text: StringDesc) {
        _text = text
    }

    override fun textBackgroundColor(textBackgroundColor: Color?) {
        _textBackgroundColor = textBackgroundColor
    }

    override fun textColor(textColor: Color?) {
        _textColor = textColor
    }

    override fun onClick(onClick: (() -> Unit)?) {
        _onClick = onClick
    }

    override fun url(url: String?) {
        _url = url ?: ""
    }

    override fun placeholder(placeholder: ImageResource?) {
        _placeholder = placeholder?.drawableResId
    }
}
