package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import ru.alex009.redwood.schema.ButtonType
import ru.alex009.redwood.schema.TextType
import ru.alex009.redwood.schema.compose.Button
import ru.alex009.redwood.schema.compose.Card
import ru.alex009.redwood.schema.compose.ImageButton
import ru.alex009.redwood.schema.compose.Stack
import ru.alex009.redwood.schema.compose.Text
import org.example.library.MR

interface ColumnProvider {
    @Composable
    fun <T> create(
        items: List<T>,
        itemContent: @Composable (item: T) -> Unit,
    )
}

@Composable
fun HelloWorld(
    columnProvider: ColumnProvider,
) {
    columnProvider.create(listOf("1","2","3")){
        Item(
            data = it,
            text = "Никого не смущает огромная популяция кенгуру, которых в австралии почти столько же, сколько и людей, проживающих там? ",
            isLike = true
        )
    }
}

@Composable
fun Item(data: String, text: String, isLike: Boolean) {
    var _isLike: Boolean by remember { mutableStateOf(isLike) }
    Column(padding = Padding(bottom = 16)) {
        Card {
            Column(
                padding = Padding(16)
            ) {
                Text(
                    text = data,
                    isSingleLine = true,
                    textType = TextType.Secondary,
                    layoutModifier = LayoutModifier.padding(Padding(bottom = 16))
                )
                Text(
                    text = text,
                    isSingleLine = false,
                    textType = TextType.Primary,
                    layoutModifier = LayoutModifier.padding(Padding(bottom = 16))
                )
                Row(
                    width = Constraint.Fill,
                    horizontalAlignment = MainAxisAlignment.End
                ) {
                    ImageButton(
                        text = "17",
                        icon = null,
                        isClicked = _isLike,
                        onClick = { _isLike = !_isLike },
                        layoutModifier = LayoutModifier.padding(Padding(end = 8))
                    )
                    ImageButton(
                        text = "9",
                        icon = null,
                        isClicked = _isLike,
                        onClick = { _isLike = !_isLike },
                    )
                }
            }
        }
    }
}
