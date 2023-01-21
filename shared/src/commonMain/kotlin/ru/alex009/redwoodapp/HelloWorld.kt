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
import ru.alex009.redwood.schema.compose.MySuperLazyColumn

interface ColumnProvider {
    @Composable
    fun <T> create(
        items: List<T>,
        itemContent: @Composable (item: T) -> Unit,
    )
}

@Composable
fun HelloWorld(
) {
   MySuperLazyColumn(listOf(
       { Text( "text1") },
       { Text("text2") },
       { Text("text3") },
       { Text("text4") },
       { Text("text5") },
       { Text("text6") },
       { Text("text7") },
       { Text("text8") },
       { Text("text9") },
       { Text("text10") },
       { Text("text11") },
       { Text("text12") },
       { Text("text13") },
       { Text("text14") },
       { Text("text15") },
       { Text("text16") },
       { Text("text17") },
       { Text("text18") },
       { Text("text21") },
       { Text("text22") },
       { Text("text23") },
       { Text("text24") },
       { Text("text25") },
       { Text("text26") },
       { Text("text27") },
       { Text("text28") },
       { Text("text29") },
       { Text("text30") },
       { Text("text31") },
       { Text("text32") },
       { Text("text33") },
       { Text("text34") },
       { Text("text35") },
       { Text("text36") },
       { Text("text37") },
       { Text("text38") },
       { Text("text41") },
       { Text("text42") },
       { Text("text43") },
       { Text("text44") },
       { Text("text45") },
       { Text("text46") },
       { Text("text47") },
       { Text("text48") },
       { Text("text49") },
       { Text("text50") },
       { Text("text51") },
       { Text("text52") },
       { Text("text53") },
       { Text("text54") },
       { Text("text55") },
       { Text("text56") },
       { Text("text57") },
       { Text("text58") }
   ))
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
