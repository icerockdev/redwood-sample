package ru.alex009.redwoodapp.navigation

import androidx.compose.runtime.Composable
import app.cash.redwood.widget.Widget
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import ru.alex009.redwoodapp.ComposeViewController

typealias FlatRouteData = (Widget.Provider<UIView>, Navigator, Map<String, String>) -> UIViewController

actual fun navigation(
    startDestination: String,
    block: FlatNavigationDsl.() -> Unit
): NavigationHost {
    val routes: MutableMap<String, FlatRouteData> = mutableMapOf()
    val dsl = object : FlatNavigationDsl {
        override fun registerScreen(
            uri: String,
            screen: @Composable (Navigator, Map<String, String>) -> Unit
        ) {
            val startUri = uri.substringBefore('?')
            routes[startUri] = { provider, navigator, args ->
                ComposeViewController(provider) @Composable {
                    screen(navigator, args)
                }
            }
        }

        override fun registerNavigation(
            uri: String,
            childNavigation: (Navigator, Map<String, String>) -> NavigationHost
        ) {
            val startUri = uri.substringBefore('?')
            routes[startUri] = { provider, navigator, args ->
                childNavigation(navigator, args).createViewController(provider)
            }
        }
    }
    dsl.block()
    return FlatNavigation(startDestination, routes)
}