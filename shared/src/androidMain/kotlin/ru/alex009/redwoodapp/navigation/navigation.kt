package ru.alex009.redwoodapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.widget.Widget
import ru.alex009.redwoodapp.ViewModelOwner

typealias FlatRouteData = @Composable (Widget.Provider<() -> Unit>, Navigator, Map<String, String>) -> Unit

actual fun navigation(
    startDestination: String,
    block: FlatNavigationDsl.() -> Unit
): NavigationHost {
    val routes: MutableMap<String, FlatRouteData> = mutableMapOf()
    val navBarVisibility: MutableMap<String, Boolean> = mutableMapOf()
    val screenSettings = ScreenSettingsImpl()
    val dsl = object : FlatNavigationDsl {
        override fun registerScreen(
            uri: String,
            isToolbarVisible: Boolean,
            screen: @Composable (
                Navigator,
                Map<String, String>,
                ScreenSettings,
                ViewModelOwner
            ) -> Unit) {
            routes[uri] = { provider, navigator, args ->
                val owner = requireNotNull(LocalViewModelStoreOwner.current)
                RedwoodContent(provider = provider) {
                    CompositionLocalProvider(LocalViewModelStoreOwner provides owner) {
                        screen(navigator, args, screenSettings, ViewModelOwner())
                    }
                }
            }
            navBarVisibility[uri] = isToolbarVisible
        }

        override fun registerNavigation(
            uri: String,
            isToolbarVisible: Boolean,
            childNavigation: (Navigator, Map<String, String>, ScreenSettings, ViewModelOwner) -> NavigationHost
        ) {
            routes[uri] = @Composable { provider, navigator, args ->
                childNavigation(navigator, args, screenSettings, ViewModelOwner()).Render(provider)
            }
            navBarVisibility[uri] = isToolbarVisible
        }
    }
    dsl.block()
    return FlatNavigation(startDestination, routes, navBarVisibility, screenSettings)
}

