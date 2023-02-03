package dev.icerock.redwoodapp.navigation

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.redwoodapp.ViewModelOwner

interface FlatNavigationDsl<T: Any> {
    fun registerScreen(
        uri: String,
        isToolbarVisible: Boolean = true,
        screen: @Composable (
            Navigator,
            Map<String, String>,
            ScreenSettings<T>,
            ViewModelOwner
        ) -> Unit
    )

    fun registerNavigation(
        uri: String,
        isToolbarVisible: Boolean = true,
        childNavigation: (
            Navigator,
            Map<String, String>,
            ScreenSettings<T>,
            ViewModelOwner
        ) -> NavigationHost
    )
}

expect fun <T : Any> navigation(
    startDestination: String,
    factory: FlatNavigationFactory<T>,
    block: FlatNavigationDsl<T>.() -> Unit,
): NavigationHost

interface ScreenSettings<T> {
    fun setToolbarData(title: T)
}

expect interface FlatNavigationFactory<T>