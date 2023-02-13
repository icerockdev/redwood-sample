package dev.icerock.redwoodapp.android.types

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryText(text: String, isSingleLine: Boolean) {
    Text(
        text = text,
        color = Color.Black,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}

@Composable
fun SecondaryText(text: String, isSingleLine: Boolean) {
    Text(
        text = text,
        color = Color.Gray,
        fontSize = 12.sp,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}

@Composable
fun HeaderText(text: String, isSingleLine: Boolean) {
    Text(
        text = text,
        color = Color.Black,
        fontSize = 25.sp,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}

@Composable
fun BoldText(text: String, isSingleLine: Boolean) {
    Text(
        text = text,
        color = Color.Black,
        fontSize = 17.sp,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}