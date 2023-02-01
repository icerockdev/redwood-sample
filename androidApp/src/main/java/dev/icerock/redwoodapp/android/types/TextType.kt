package dev.icerock.redwoodapp.android.types

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import dev.icerock.redwoodapp.android.R
import dev.icerock.redwoodapp.android.theme.LocalColors

@Composable
fun PrimaryText(text: String, isSingleLine: Boolean) {
    Text(
        text = text,
        color = LocalColors.current.blackText,
        fontSize = 20.sp,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}

@Composable
fun SecondaryText(text: String, isSingleLine: Boolean) {
    Text(
        text = text,
        color = LocalColors.current.grayText,
        fontSize = 14.sp,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}

@Composable
fun HeaderText(text: String, isSingleLine: Boolean) {
    Text(
        text = text,
        color = LocalColors.current.blackText,
        fontFamily = FontFamily(Font(R.font.slackey_regular)),
        fontSize = 20.sp,
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE
    )
}