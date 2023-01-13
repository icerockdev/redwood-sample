package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import dev.icerock.moko.graphics.Color

@Composable
fun ApplicationMainTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = appLightColors()
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(
        values = arrayOf(
            LocalColors provides rememberedColors
        )
    ) {
        content()
    }
}

class AppColors(
    background: Color,
    isLight: Boolean
) {
    var background by mutableStateOf(background)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        background: Color = this.background,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        background,
        isLight
    )

    // will be explained later
    fun updateColorsFrom(other: AppColors) {
        background = other.background
        isLight = other.isLight
    }
}

fun appLightColors(
    background: Color = primary95,
): AppColors = AppColors(
    background = background,
    isLight = true
)

internal val LocalColors = staticCompositionLocalOf { appLightColors() }
