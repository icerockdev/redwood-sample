package dev.icerock.redwoodapp.navigation

import androidx.compose.runtime.Composable
import app.cash.redwood.widget.Widget
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import dev.icerock.redwoodapp.ComposeViewController
import dev.icerock.redwoodapp.ViewModelOwner

typealias FlatRouteData = (Widget.Provider<UIView>, Navigator, Map<String, String>, ViewModelOwner) -> UIViewController

actual fun <T : Any> navigation(
    startDestination: String,
    factory: FlatNavigationFactory<T>,
    block: FlatNavigationDsl<T>.() -> Unit
): NavigationHost {
    val routes: MutableMap<String, FlatRouteData> = mutableMapOf()
    val viewModelOwners: MutableMap<String, ViewModelOwner> = mutableMapOf()

    val dsl = object : FlatNavigationDsl<T> {
        override fun registerScreen(
            uri: String,
            isToolbarVisible: Boolean,
            screen: @Composable (
                Navigator,
                Map<String, String>,
                ScreenSettings<T>,
                ViewModelOwner
            ) -> Unit
        ) {
            val startUri = uri.substringBefore('?')
            routes[startUri] = { provider, navigator, args, viewModelOwner ->
                val screenSettings = ScreenSettingsImpl(factory)
                ComposeViewController(
                    provider,
                    viewModelOwner
                ) @Composable {
                    screen(
                        navigator,
                        args,
                        screenSettings,
                        viewModelOwner
                    )
                }.also {
                    screenSettings.init(it)
                }
            }
            viewModelOwners[startUri] = ViewModelOwner(mutableMapOf())
        }

        override fun registerNavigation(
            uri: String,
            isToolbarVisible: Boolean,
            childNavigation: (Navigator, Map<String, String>, ScreenSettings<T>, ViewModelOwner) -> NavigationHost
        ) {
            val startUri = uri.substringBefore('?')
            routes[startUri] = { provider, navigator, args, viewModelOwner ->
                val screenSettings = ScreenSettingsImpl(factory)
                childNavigation(
                    navigator,
                    args,
                    screenSettings,
                    viewModelOwner
                ).createViewController(provider).also {
                    screenSettings.init(it)
                }
            }
            viewModelOwners[startUri] = ViewModelOwner(mutableMapOf())
        }
    }
    dsl.block()
    return FlatNavigation<T>(
        startDestination = startDestination,
        routes = routes,
        viewModelOwners = viewModelOwners
    )
}