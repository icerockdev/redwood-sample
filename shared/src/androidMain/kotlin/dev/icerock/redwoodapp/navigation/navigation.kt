package dev.icerock.redwoodapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.widget.Widget
import dev.icerock.redwoodapp.ViewModelOwner

typealias FlatRouteData = @Composable (Widget.Provider<() -> Unit>, Navigator, Map<String, String>) -> Unit

actual fun <T: Any> navigation(
    startDestination: String,
    factory: FlatNavigationFactory<T>,
    block: FlatNavigationDsl<T>.() -> Unit,
): NavigationHost {
    val routes: MutableMap<String, FlatRouteData> = mutableMapOf()
    val navBarVisibility: MutableMap<String, Boolean> = mutableMapOf()
    val screenSettings = ScreenSettingsImpl<T>()
    val dsl = object : FlatNavigationDsl<T> {
        override fun registerScreen(
            uri: String,
            isToolbarVisible: Boolean,
            screen: @Composable (
                Navigator,
                Map<String, String>,
                ScreenSettings<T>,
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
            childNavigation: (Navigator, Map<String, String>, ScreenSettings<T>, ViewModelOwner) -> NavigationHost
        ) {
            routes[uri] = @Composable { provider, navigator, args ->
                childNavigation(navigator, args, screenSettings, ViewModelOwner()).Render(provider)
            }
            navBarVisibility[uri] = isToolbarVisible
        }
    }
    dsl.block()
    return FlatNavigation(startDestination, routes, navBarVisibility, screenSettings, factory)
}

actual interface FlatNavigationFactory<T>{

    @Composable
    fun RenderToolbar(navController: NavController, data: T)
}