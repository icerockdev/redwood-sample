package ru.alex009.redwoodapp.android.types

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryText(text: String, isSingleLine: Boolean) {
    Text(
        text = text,
        color = Color.Black,
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