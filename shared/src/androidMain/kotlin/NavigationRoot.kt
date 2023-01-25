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
    @Composable
    abstract fun render(navigator: Navigator, provider: Widget.Provider<@Composable () -> Unit>)

    class NavigationSimple(private val startDestination: String, private val routes: MutableMap<String, NavigationRoot>) :
        NavigationRoot() {

        @Composable
        override fun render(
            navigator: Navigator,
            provider: Widget.Provider<@Composable () -> Unit>
        ) {
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

            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                routes.forEach { item ->
                    composable(route = item.key) {
                        Scaffold(
                            content = { innerPadding ->
                                Box(modifier = Modifier.padding(innerPadding)) {
                                    RedwoodContent(provider) {
                                        item.value.render(navigator, provider)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    class NavigationTabs(private val startDestination: String, private val routes: MutableMap<String, NavigationRoot>) :
        NavigationRoot() {
        @Composable
        override fun render(navigator: Navigator, provider: Widget.Provider<() -> Unit>) {
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
                startDestination = startDestination
            ) {
                routes.forEach { item ->
                    composable(route = item.key) {
                        Scaffold(
                            bottomBar = {

                            },
                            content = { innerPadding ->
                                Box(modifier = Modifier.padding(innerPadding)) {
                                    RedwoodContent(provider) {
                                        item.value.render(navigator, provider)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    class Simple(private val composeFun: @Composable (Navigator) -> Unit) : NavigationRoot() {
        @Composable
        override fun render(navigator: Navigator, provider: Widget.Provider<() -> Unit>) {
            composeFun(navigator)
        }

    }

    class SimpleWithArgs<T>(private val args: T? = null, private val composeFun: @Composable (Navigator, T?) -> Unit) : NavigationRoot() {
        @Composable
        override fun render(navigator: Navigator, provider: Widget.Provider<() -> Unit>) {
            composeFun(navigator, args)
        }

    }
}


actual fun navigation(startDestination: String, block: NavigationDsl.() -> Unit): NavigationRoot {
    val routes: MutableMap<String, NavigationRoot> =
        mutableMapOf<String, NavigationRoot>()
    val dsl = object : NavigationDsl {
        override fun register(uri: String, screen: @Composable (Navigator) -> Unit) {
            routes[uri] = NavigationRoot.Simple(null, screen)
        }

        override fun register(uri: String, navigationRoot: NavigationRoot) {
            routes[uri] = navigationRoot
        }

        override fun <T> register(uri: String, args: T, screen: (Navigator, T) -> Unit) {
            routes[uri] = NavigationRoot.Simple(args,screen)
        }
    }
    dsl.block()
    return NavigationRoot.NavigationSimple(startDestination, routes)
}

actual fun navigationTabs(startDestination: String ,block: NavigationDsl.() -> Unit): NavigationRoot {
    val routes: MutableMap<String, NavigationRoot> =
        mutableMapOf<String, NavigationRoot>()
    val dsl = object : NavigationDsl {
        override fun register(uri: String, screen: @Composable (Navigator) -> Unit) {
            routes[uri] = NavigationRoot.Simple(screen)
        }

        override fun register(uri: String, navigationRoot: NavigationRoot) {
            routes[uri] = navigationRoot
        }
    }
    dsl.block()
    return NavigationRoot.NavigationTabs(startDestination, routes)
}