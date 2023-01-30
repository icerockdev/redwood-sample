package ru.alex009.redwoodapp.navigation

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.desc.StringDesc

interface FlatNavigationDsl {
    fun registerScreen(
        uri: String,
        isToolbarVisible: Boolean = true,
        screen: @Composable (Navigator, Map<String, String>, ScreenSettings) -> Unit
    )

    fun registerNavigation(
        uri: String,
        isToolbarVisible: Boolean = true,
        childNavigation: (Navigator, Map<String, String>, ScreenSettings) -> NavigationHost
    )
}

expect fun navigation(
    startDestination: String,
    block: FlatNavigationDsl.() -> Unit
): NavigationHost

interface ScreenSettings {
    fun setTitle(title: StringDesc)
}