package dev.icerock.redwoodapp.android.types

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import dev.icerock.redwoodapp.android.theme.TextStyles

@Composable
fun TextWithStyle(text: String, isSingleLine: Boolean, textStyle: TextStyle) {
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        style = textStyle,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}

private fun String.parseBold(): AnnotatedString {
    val parts = this.split("<b>", "</b>")
    return buildAnnotatedString {
        var bold = false
        for (part in parts) {
            if (bold) {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(part)
                }
            } else {
                append(part)
            }
            bold = !bold
        }
    }
}