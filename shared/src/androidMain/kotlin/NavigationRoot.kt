package ru.alex009.redwoodapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.widget.Widget

actual sealed class NavigationRoot {
    @Composable
    abstract fun Render(navigator: Navigator, provider: Widget.Provider<@Composable () -> Unit>)

    class NavigationSimple(
        private val startDestination: String,
        private val routes: MutableMap<String, NavigationRoot>
    ) : NavigationRoot() {

        @Composable
        override fun Render(
            navigator: Navigator,
            provider: Widget.Provider<@Composable () -> Unit>
        ) {
            val navController = rememberNavController()
            val nav = remember {
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
                                    item.value.Render(nav, provider)
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    class NavigationTabs(
        private val startDestination: String,
        private val routes: MutableMap<String, NavigationRoot>
    ) : NavigationRoot() {

        @Composable
        override fun Render(navigator: Navigator, provider: Widget.Provider<() -> Unit>) {
            val navController = rememberNavController()
            val nav = remember {
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
                            bottomBar = {
                                BottomNavigation(modifier = Modifier.background(Color.White)) {
                                    val screens = listOf(
                                        "Tab 1" to Pair("tab1", ""),
                                        "Tab 2" to Pair("tab2", "")
                                    )
                                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                                    val currentRoute = navBackStackEntry?.destination?.route
                                    screens.forEach { screen ->
                                        val destination = screen.second
                                        BottomNavigationItem(
                                            modifier = Modifier.background(Color.White),
                                            label = {
                                                Text(
                                                    text = screen.second.first,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                            },
                                            selected = currentRoute == destination.first,
                                            selectedContentColor = Color.Black,
                                            unselectedContentColor = Color.Gray,
                                            onClick = {
                                                navController.navigate(destination.first) {
                                                    popUpTo(navController.graph.startDestinationId)
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            },
                                            icon = {
                                                Icon(
                                                    painter = painterResource(id = android.R.drawable.btn_star_big_on),
                                                    contentDescription = null)
                                            }
                                        )
                                    }
                                }
                            },
                            content = { innerPadding ->
                                Box(modifier = Modifier.padding(innerPadding)) {
                                    item.value.Render(nav, provider)
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
        override fun Render(navigator: Navigator, provider: Widget.Provider<() -> Unit>) {
            RedwoodContent(provider) {
                composeFun(navigator)
            }
        }

    }
}

actual fun navigation(startDestination: String, block: NavigationDsl.() -> Unit): NavigationRoot {
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
    return NavigationRoot.NavigationSimple(startDestination, routes)
}

actual fun navigationTabs(
    startDestination: String,
    block: NavigationDsl.() -> Unit
): NavigationRoot {
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
