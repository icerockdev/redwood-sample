@file:OptIn(ExperimentalMaterialApi::class)

package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.compose.ComposeWidgetChildren
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.graphics.colorInt
import dev.icerock.redwood.schema.widget.ListItem
import dev.icerock.redwoodapp.android.theme.Colors
import dev.icerock.redwoodapp.android.theme.TextStyles

class ComposeListItem : ListItem<@Composable () -> Unit> {
    override val child = ComposeWidgetChildren()

    private var _tintColor: Color? by mutableStateOf(null)
    private var _height: Int? by mutableStateOf(null)
    private var _onClick: (() -> Unit)? by mutableStateOf(null)
    private var _title: StringDesc? by mutableStateOf(null)
    private var _subtitle: StringDesc? by mutableStateOf(null)
    private var _icon: ImageResource? by mutableStateOf(null)

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .let {
                    val onCLick = _onClick
                    if (onCLick != null) {
                        it.clickable { onCLick() }
                    } else {
                        it
                    }
                },
            text = {
                Text(
                    text = _title?.toString(LocalContext.current) ?: "",
                    style = TextStyles.primary,
                    color = _tintColor?.let { color ->
                        androidx.compose.ui.graphics.Color(
                            color.rgba
                        )
                    } ?: Colors.black,
                    maxLines = 1,
                )
            },
            icon = _icon?.let {
                {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = it.drawableResId),
                        contentDescription = null,
                        colorFilter = _tintColor?.let { color ->
                            ColorFilter.tint(androidx.compose.ui.graphics.Color(
                                color.rgba
                            )
                            )
                        },
                        contentScale = ContentScale.Inside
                    )
                }
            },
            secondaryText = _subtitle?.let {
                {
                    Text(
                        text = it.toString(LocalContext.current),
                        style = TextStyles.secondarySmall,
                        color = _tintColor?.let { color ->
                            androidx.compose.ui.graphics.Color(
                                color.rgba
                            )
                        } ?: Colors.black
                    )
                }
            },
            trailing = {
                child.render()
            }
        )
    }

    override fun title(title: StringDesc) {
        _title = title
    }

    override fun subtitle(subtitle: StringDesc?) {
        _subtitle = subtitle
    }

    override fun icon(icon: ImageResource?) {
        _icon = icon
    }

    override fun height(height: Int?) {
        _height = height
    }

    override fun tintColor(tintColor: Color?) {
        _tintColor = tintColor
    }

    override fun onClick(onClick: (() -> Unit)?) {
        _onClick = onClick
    }
}