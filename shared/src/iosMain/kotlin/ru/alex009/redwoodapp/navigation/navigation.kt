package ru.alex009.redwoodapp.navigation

import androidx.compose.runtime.Composable
import app.cash.redwood.widget.Widget
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import ru.alex009.redwoodapp.ComposeViewController
import ru.alex009.redwoodapp.ViewModelOwner
import ru.alex009.redwoodapp.ru.alex009.redwoodapp.navigation.ScreenSettingsImpl

typealias FlatRouteData = (Widget.Provider<UIView>, Navigator, Map<String, String>, ViewModelOwner) -> UIViewController

actual fun navigation(
    startDestination: String,
    block: FlatNavigationDsl.() -> Unit
): NavigationHost {
    val routes: MutableMap<String, FlatRouteData> = mutableMapOf()
    val navBarVisibility: MutableMap<String, Boolean> = mutableMapOf()
    val screenSettings = ScreenSettingsImpl()
    val viewModelOwners: MutableMap<String, ViewModelOwner> = mutableMapOf()

    val dsl = object : FlatNavigationDsl {
        override fun registerScreen(
            uri: String,
            isToolbarVisible: Boolean,
            screen: @Composable (
                Navigator,
                Map<String, String>,
                ScreenSettings,
                ViewModelOwner
            ) -> Unit
        ) {
            val startUri = uri.substringBefore('?')
            routes[startUri] = { provider, navigator, args, viewModelOwner ->
                ComposeViewController(
                    provider,
                    navBarVisibility[startUri] ?: true,
                    viewModelOwner
                ) @Composable {
                    screen(
                        navigator,
                        args,
                        screenSettings,
                        viewModelOwner
                    )
                }
            }
            viewModelOwners[startUri] = ViewModelOwner(mutableMapOf())
            navBarVisibility[startUri] = isToolbarVisible
        }

        override fun registerNavigation(
            uri: String,
            isToolbarVisible: Boolean,
            childNavigation: (Navigator, Map<String, String>, ScreenSettings, ViewModelOwner) -> NavigationHost
        ) {
            val startUri = uri.substringBefore('?')
            routes[startUri] = { provider, navigator, args, viewModelOwner ->
                childNavigation(
                    navigator,
                    args,
                    screenSettings,
                    viewModelOwner
                ).createViewController(provider)
            }
            viewModelOwners[startUri] = ViewModelOwner(mutableMapOf())
            navBarVisibility[startUri] = isToolbarVisible
        }
    }
    dsl.block()
    return FlatNavigation(
        startDestination = startDestination,
        routes = routes,
        navBarVisibility = navBarVisibility,
        viewModelOwners = viewModelOwners,
        screenSettings = screenSettings
    )
}