package dev.icerock.redwoodapp.android.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object TextStyles {

    val h1: TextStyle = TextStyle(
        color = Colors.black,
        fontSize = 28.sp,
        fontWeight = FontWeight(600),
        lineHeight = 36.sp
    )

    val h2: TextStyle = TextStyle(
        color = Colors.black,
        fontSize = 20.sp,
        fontWeight = FontWeight(600),
        lineHeight = 28.sp
    )

    val primary: TextStyle = TextStyle(
        color = Colors.black,
        fontSize = 20.sp,
        fontWeight = FontWeight(400),
        lineHeight = 24.sp
    )

    val primaryBold: TextStyle = TextStyle(
        color = Colors.black,
        fontSize = 20.sp,
        fontWeight = FontWeight(600),
        lineHeight = 24.sp
    )

    val primarySmall: TextStyle = TextStyle(
        color = Colors.black,
        fontSize = 16.sp,
        fontWeight = FontWeight(500),
        lineHeight = 20.sp
    )

    val secondary: TextStyle = TextStyle(
        color = Colors.gray90,
        fontSize = 15.sp,
        fontWeight = FontWeight(400),
        lineHeight = 20.sp
    )

    val secondarySmall: TextStyle = TextStyle(
        color = Colors.black,
        fontSize = 13.sp,
        fontWeight = FontWeight(400),
        lineHeight = 17.sp
    )

    val caption: TextStyle = TextStyle(
        color = Colors.gray70,
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        lineHeight = 16.sp
    )

    val link: TextStyle = TextStyle(
        color = Colors.primary,
        fontSize = 14.sp,
        fontWeight = FontWeight(500),
        lineHeight = 20.sp
    )

    val primaryRed: TextStyle = TextStyle(
        color = Colors.error,
        fontSize = 20.sp,
        fontWeight = FontWeight(400),
        lineHeight = 24.sp
    )
}