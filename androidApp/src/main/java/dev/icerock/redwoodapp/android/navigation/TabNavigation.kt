/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwoodapp.android.navigation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.widget.Widget
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.redwood.navigation.NavigationHost
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.navigation.scope.NavigationTabsScope
import dev.icerock.redwoodapp.android.theme.Colors

data class TabRouteData(
    val title: StringDesc?,
    val icon: ImageResource?,
    val render: @Composable (Widget.Provider<() -> Unit>, Navigator) -> Unit
) {
    init {
        assert(title != null || icon != null) { "tab should have at least title or icon" }
    }
}

data class TabNavigation(
    val startDestination: String,
    val routes: Map<String, TabRouteData>
) : NavigationHost {

    @Composable
    override fun Render(provider: Widget.Provider<() -> Unit>) {
        val navController: NavHostController = rememberNavController()
        val nav: Navigator = remember(navController) {
            object : Navigator {
                override fun navigate(uri: String) {
                    navController.navigate(uri)
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
                            BottomBarNavigation(navController)
                        },
                        content = { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)
                                .background(Colors.gray60)) {
                                item.value.render(provider, nav)
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun BottomBarNavigation(navController: NavHostController) {
        BottomNavigation {
            val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
            val currentRoute: String? = navBackStackEntry?.destination?.route

            routes.forEach { (key: String, data: TabRouteData) ->
                BottomBarItem(data, currentRoute, key, navController)
            }
        }
    }

    @Composable
    private fun RowScope.BottomBarItem(
        data: TabRouteData,
        currentRoute: String?,
        key: String,
        navController: NavHostController
    ) {
        val context: Context = LocalContext.current

        BottomNavigationItem(
            modifier = Modifier.background(Color.White),
            label = {
                val title: StringDesc = data.title ?: return@BottomNavigationItem
                Text(text = title.toString(context))
            },
            selected = currentRoute == key,
            selectedContentColor = Color(0xFF684CDC),
            unselectedContentColor = Color.Gray,
            onClick = {
                navController.navigate(key) {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                val icon: ImageResource = data.icon ?: return@BottomNavigationItem

                Icon(
                    painter = painterResource(id = icon.drawableResId),
                    contentDescription = null
                )
            }
        )
    }

    companion object {
        operator fun invoke(
            startDestination: String,
            block: NavigationTabsScope.() -> Unit
        ): TabNavigation {
            val routes: MutableMap<String, TabRouteData> = mutableMapOf()
            RoutesRegister(routes).block()

            return TabNavigation(
                startDestination = startDestination,
                routes = routes
            )
        }
    }

    private class RoutesRegister(
        private val routes: MutableMap<String, TabRouteData>
    ) : NavigationTabsScope {
        override fun registerNavigation(
            uri: String,
            title: StringDesc?,
            icon: ImageResource?,
            childNavigation: (Navigator) -> NavigationHost
        ) {
            routes[uri] = TabRouteData(
                title = title,
                icon = icon,
                render = @Composable { provider, navigator ->
                    childNavigation(navigator).Render(provider)
                }
            )
        }

        override fun registerScreen(
            uri: String,
            title: StringDesc?,
            icon: ImageResource?,
            screen: @Composable (Navigator) -> Unit
        ) {
            routes[uri] = TabRouteData(
                title = title,
                icon = icon,
                render = @Composable { provider, navigator ->
                    val owner: ViewModelStoreOwner =
                        requireNotNull(LocalViewModelStoreOwner.current)
                    RedwoodContent(provider = provider) {
                        CompositionLocalProvider(LocalViewModelStoreOwner provides owner) {
                            screen(navigator)
                        }
                    }
                }
            )
        }
    }
}
