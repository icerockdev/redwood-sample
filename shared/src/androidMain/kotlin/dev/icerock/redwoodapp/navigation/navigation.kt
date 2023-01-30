package dev.icerock.redwoodapp.navigation

import androidx.compose.runtime.Composable
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.widget.Widget

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
            screen: @Composable (Navigator, Map<String, String>, ScreenSettings) -> Unit
        ) {
            routes[uri] = { provider, navigator, args ->
                RedwoodContent(provider = provider) {
                    screen(navigator, args, screenSettings)
                }
            }
            navBarVisibility[uri] = isToolbarVisible
        }

        override fun registerNavigation(
            uri: String,
            isToolbarVisible: Boolean,
            childNavigation: (Navigator, Map<String, String>, ScreenSettings) -> NavigationHost
        ) {
            routes[uri] = @Composable { provider, navigator, args ->
                childNavigation(navigator, args, screenSettings).Render(provider)
            }
            navBarVisibility[uri] = isToolbarVisible
        }
    }
    dsl.block()
    return FlatNavigation(startDestination, routes, navBarVisibility, screenSettings)
}

