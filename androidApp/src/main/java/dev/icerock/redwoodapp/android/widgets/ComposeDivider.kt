package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.widget.Divider
import dev.icerock.redwood.schema.widget.Text
import dev.icerock.redwoodapp.android.theme.Colors
import dev.icerock.redwoodapp.android.types.BoldText
import dev.icerock.redwoodapp.android.types.HeaderText
import dev.icerock.redwoodapp.android.types.PrimaryText
import dev.icerock.redwoodapp.android.types.SecondaryText

class ComposeDivider : Divider<@Composable () -> Unit> {

    private var _isVertical by mutableStateOf(true)
    override fun isVertical(isVertical: Boolean) {
        _isVertical = isVertical
    }

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        Spacer(modifier = Modifier
            .background(Colors.gray70).let {
                if (_isVertical) {
                    it
                        .height(1.dp)
                        .fillMaxWidth()
                } else {
                    it
                        .fillMaxHeight()
                        .width(1.dp)
                }
            })
    }

}
