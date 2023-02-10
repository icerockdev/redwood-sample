package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import app.cash.redwood.LayoutModifier
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.widget.CounterButton
import dev.icerock.redwoodapp.android.R
import dev.icerock.redwoodapp.android.theme.Colors

class ComposeCounterButton : CounterButton<@Composable () -> Unit> {
    private var _text: StringDesc by mutableStateOf("".desc())
    private var _onAddClick: () -> Unit by mutableStateOf({})
    private var _onRemoveClick: () -> Unit by mutableStateOf({})
    private var _size: Size by mutableStateOf(Size.Wrap)

    override fun count(count: StringDesc) {
        _text = count
    }

    override fun onAddClick(onAddClick: (() -> Unit)?) {
        _onAddClick = onAddClick ?: {}
    }

    override fun onRemoveClick(onRemoveClick: (() -> Unit)?) {
        _onRemoveClick = onRemoveClick ?: {}
    }

    override fun width(width: Size) {
        _size = width
    }

    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        Row(
            modifier = Modifier
                .width(_size)
                .clip(CircleShape)
                .background(Colors.gray60),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { _onRemoveClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_horizontal_rule_24),
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = _text.toString(LocalContext.current))
            }

            IconButton(onClick = { _onAddClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = null
                )
            }
        }
    }
}
