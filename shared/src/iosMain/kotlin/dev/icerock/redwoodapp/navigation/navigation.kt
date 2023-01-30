package dev.icerock.redwoodapp.navigation

import androidx.compose.runtime.Composable
import app.cash.redwood.widget.Widget
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import dev.icerock.redwoodapp.ComposeViewController
import dev.icerock.redwoodapp.dev.icerock.redwoodapp.navigation.ScreenSettingsImpl

typealias FlatRouteData = (Widget.Provider<UIView>, Navigator, Map<String, String>) -> UIViewController

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
            val startUri = uri.substringBefore('?')
            routes[startUri] = { provider, navigator, args ->
                ComposeViewController(provider, navBarVisibility[startUri] ?: true) @Composable {
                    screen(navigator, args, screenSettings)
                }
            }
            navBarVisibility[startUri] = isToolbarVisible
        }

        override fun registerNavigation(
            uri: String,
            isToolbarVisible: Boolean,
            childNavigation: (Navigator, Map<String, String>, ScreenSettings) -> NavigationHost
        ) {
            val startUri = uri.substringBefore('?')
            routes[startUri] = { provider, navigator, args ->
                childNavigation(navigator, args, screenSettings).createViewController(provider)
            }
            navBarVisibility[startUri] = isToolbarVisible
        }
    }
    dsl.block()
    return FlatNavigation(startDestination, routes, navBarVisibility, screenSettings)
}