package ru.alex009.redwoodapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.widget.Widget

sealed class NavigationRoot {
    class NavigationSimple(routes: MutableMap<String, @Composable (Navigator) -> Unit>) : NavigationRoot()
    class NavigationTabs() : NavigationRoot()
}
class NavigationSimple(routes: MutableMap<String, @Composable (Navigator) -> Unit>) {
    private val _routes = routes

    @Composable
    fun render(provider: Widget.Provider<@Composable () -> Unit>) {
        val navController = rememberNavController()
        val navigator = remember {
            object : Navigator {
                override fun navigate(uri: String) {
                    navController.navigate(uri)
                }

                override fun popBackStack() {
                    navController.popBackStack()
                }
            }
        }

        var currentScreen: String by remember { mutableStateOf("") }

        NavHost(
            navController = navController,
            startDestination = "auth"
        ) {
            _routes.forEach { item ->
                composable(route = item.key) {
                    Scaffold(
                        bottomBar = {
                        },
                        content = { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
                                RedwoodContent(provider) {
                                    item.value(navigator)
                                }
                            }
                        }
                    )
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

        override fun register(uri: String, navigationRoot: NavigationRoot) {
            //TODO("Not yet implemented")
        }
    }
    dsl.block()
    return NavigationSimple(routes) as NavigationRoot
}

actual fun navigationTabs(block: NavigationDsl.() -> Unit): NavigationRoot {
    val routes: MutableMap<String, @Composable (Navigator) -> Unit> =
        mutableMapOf<String, @Composable (Navigator) -> Unit>()
    val dsl = object : NavigationDsl {
        override fun register(uri: String, screen: @Composable (Navigator) -> Unit) {
            routes[uri] = screen
        }

        override fun register(uri: String, navigationRoot: NavigationRoot) {

        }
    }
    dsl.block()
    return NavigationRoot(routes)
}