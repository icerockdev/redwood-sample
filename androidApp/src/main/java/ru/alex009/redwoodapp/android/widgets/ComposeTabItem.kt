package ru.alex009.redwoodapp.android.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.redwood.LayoutModifier
import dev.icerock.moko.resources.ImageResource
import ru.alex009.redwood.schema.widget.TabItem
import ru.alex009.redwoodapp.android.R

class ComposeTabItem: TabItem<@Composable () -> Unit> {
    private var _text: String by mutableStateOf("")
    private var _icon: ImageResource? by mutableStateOf(null)
    private var _isClicked by mutableStateOf(true)
    private var _onClick: () -> Unit by mutableStateOf({})

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(30.dp)
                    .padding(4.dp),
                painter = painterResource(id = _icon?.drawableResId ?: R.drawable.like),
                contentDescription = null,
                tint = if (_isClicked) Color.Black else Color.Gray
            )
            Text(
                text = _text,
                color = if (_isClicked) Color.Black else Color.Gray,
                fontSize = 10.sp
            )
        }
    }

    override fun text(text: String) {
        _text = text
    }

    override fun icon(icon: ImageResource?) {
        _icon = icon
    }

    override fun isClicked(isClicked: Boolean) {
        _isClicked = isClicked
    }

    override fun onClick(onClick: (() -> Unit)?) {
        _onClick = onClick ?: {}
    }


}
