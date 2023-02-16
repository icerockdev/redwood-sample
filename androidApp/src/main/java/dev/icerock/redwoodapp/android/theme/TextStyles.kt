package dev.icerock.redwoodapp.android.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object TextStyles {

    val header2 : TextStyle = TextStyle(
        color = Colors.black,
        fontSize = 17.sp,
        fontWeight = FontWeight(400),
        lineHeight = 22.sp
    )

    val header3 : TextStyle = TextStyle(
        color = Colors.black,
        fontSize = 22.sp,
        fontWeight = FontWeight(400),
        lineHeight = 22.sp
    )

    val body : TextStyle = TextStyle(
        color = Colors.black,
        fontSize = 14.sp,
        fontWeight = FontWeight(400),
        lineHeight = 20.sp
    )

    val bodyAccent : TextStyle = TextStyle(
        color = Colors.accent,
        fontSize = 14.sp,
        fontWeight = FontWeight(400),
        lineHeight = 20.sp
    )
}