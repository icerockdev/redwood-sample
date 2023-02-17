package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import dev.icerock.moko.graphics.colorInt
import dev.icerock.redwood.schema.widget.Chip
import dev.icerock.redwoodapp.android.theme.Colors
import dev.icerock.redwoodapp.android.theme.TextStyles

class ComposeChip : Chip<@Composable () -> Unit> {

    private var _text by mutableStateOf<StringDesc?>(null)
    private var _icon by mutableStateOf<ImageResource?>(null)
    private var _backgroundColor by mutableStateOf<Color?>(null)
    private var _textColor by mutableStateOf<Color?>(null)
    override fun text(text: StringDesc) {
        _text = text
    }

    override fun icon(icon: ImageResource?) {
        _icon = icon
    }

    override fun backgroundColor(backgroundColor: Color?) {
        _backgroundColor = backgroundColor
    }

    override fun textColor(textColor: Color?) {
        _textColor = textColor
    }

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        Row(modifier = Modifier
            .clip(RoundedCornerShape(20))
            .background(_backgroundColor?.let { androidx.compose.ui.graphics.Color(it.colorInt()) }
                ?: androidx.compose.ui.graphics.Color.Transparent)
            .padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            _icon?.let {
                val textColor = _textColor?.let { androidx.compose.ui.graphics.Color(it.colorInt()) }
                    ?: Colors.black
                Image(
                    modifier = Modifier
                        .padding(end = 8.dp, top = 4.dp, bottom = 4.dp)
                        .size(16.dp),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = it.drawableResId),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(textColor)
                )
                Text(
                    text = _text?.toString(LocalContext.current).orEmpty(),
                    color = textColor,
                    style = TextStyles.secondarySmall
                )
            }
        }

    }
}