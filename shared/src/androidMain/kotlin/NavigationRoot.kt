package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.widget.Widget

actual class NavigationRoot(routes: MutableMap<String, @Composable (Navigator) -> Unit>) {
    private val _routes = routes

    @Composable
    fun render(provider: Widget.Provider<@Composable () -> Unit>) {
        val navController = rememberNavController()
        val navigator = object : Navigator {
            override fun navigate(uri: String) {
                navController.navigate(uri)
            }
            override fun popBackStack() {
                navController.popBackStack()
            }
        }

        NavHost(
            navController = navController,
            startDestination = "list"
        ) {
            _routes.forEach { item ->
                composable(route = item.key) {
                    RedwoodContent(provider) {
                        item.value(navigator)
                    }
                }
            }
        }
    }
}

actual fun navigation(block: NavigationDsl.() -> Unit): NavigationRoot {
    val routes: MutableMap<String, @Composable (Navigator) -> Unit> =
        mutableMapOf<String, @Composable (Navigator) -> Unit>()
    val dsl = object : NavigationDsl {
        override fun register(uri: String, screen: @Composable (Navigator) -> Unit) {
            routes[uri] = screen
        }
    }
    dsl.block()
    return NavigationRoot(routes)
}
