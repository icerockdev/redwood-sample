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

@Composable
fun PrimaryText(text: String, isSingleLine: Boolean) {
    Text(
        text = text.parseBold(),
        color = Color.Black,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}

@Composable
fun SecondaryText(text: String, isSingleLine: Boolean) {
    Text(
        text = text.parseBold(),
        color = Color.Gray,
        fontSize = 12.sp,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}

@Composable
fun HeaderText(text: String, isSingleLine: Boolean) {
    Text(
        text = text.parseBold(),
        color = Color.Black,
        fontSize = 25.sp,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}

@Composable
fun BoldText(text: String, isSingleLine: Boolean) {
    Text(
        text = text.parseBold(),
        color = Color.Black,
        fontSize = 22.sp,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}

@Composable
fun PrimaryText(text: String, isSingleLine: Boolean, textStyle: TextStyle) {
    Text(
        text = text.parseBold(),
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