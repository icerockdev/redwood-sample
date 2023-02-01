package dev.icerock.redwoodapp.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        appDarkColors()
    } else {
        appLightColors()
    }

    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(
        values = arrayOf(
            LocalColors provides rememberedColors,
        )
    ) {
        content()
    }
}

class AppColors(
    background: Color,
    blackText: Color,
    grayText: Color,
    isLight: Boolean
) {
    var background by mutableStateOf(background)
        private set
    var blackText by mutableStateOf(blackText)
        private set
    var grayText by mutableStateOf(grayText)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        background: Color = this.background,
        blackText: Color = this.blackText,
        grayText: Color = this.grayText,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        background,
        blackText,
        grayText,
        isLight
    )

    // will be explained later
    fun updateColorsFrom(other: AppColors) {
        background = other.background
        blackText = other.blackText
        grayText = other.grayText
        isLight = other.isLight
    }
}

fun appLightColors(
    background: Color = Color.White,
    blackText: Color = BlackText,
    grayText: Color = GrayText
): AppColors = AppColors(
    background = background,
    blackText = blackText,
    grayText = grayText,
    isLight = true
)


fun appDarkColors(
    background: Color = Color.White,
    blackText: Color = BlackText,
    grayText: Color = GrayText
): AppColors = AppColors(
    background = background,
    blackText = blackText,
    grayText = grayText,
    isLight = true
)

internal val LocalColors = staticCompositionLocalOf { appLightColors() }