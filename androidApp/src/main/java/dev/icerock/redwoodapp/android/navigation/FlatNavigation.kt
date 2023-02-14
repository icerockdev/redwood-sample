/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwoodapp.android.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.widget.Widget
import dev.icerock.redwood.navigation.NavigationHost
import dev.icerock.redwood.navigation.navbar.LocalNavBarController
import dev.icerock.redwood.navigation.navbar.NavBarController
import dev.icerock.redwood.navigation.navbar.NavBarData
import dev.icerock.redwood.navigation.navbar.NavBarFactory
import dev.icerock.redwood.navigation.navigator.HistoryNavigator
import dev.icerock.redwood.navigation.scope.NavigationFlatScope
import dev.icerock.redwoodapp.android.navigation.navbar.AndroidNavBarFactory
import kotlinx.coroutines.flow.MutableStateFlow

private typealias FlatRouteData = @Composable (Widget.Provider<() -> Unit>, HistoryNavigator, Map<String, String>) -> Unit

data class FlatNavigation(
    val startDestination: String,
    val routes: Map<String, FlatRouteData>,
) : NavigationHost {

    @Composable
    override fun Render(provider: Widget.Provider<() -> Unit>) {
        val navController: NavHostController = rememberNavController()

        val nav: HistoryNavigator = remember(navController) {
            object : HistoryNavigator {
                override fun navigate(uri: String) {
                    navController.navigate(uri)
                }

                override fun popBackStack() {
                    navController.popBackStack()
                }

                override fun popToRoot() {
                    while (navController.backQueue.size > 2) {
                        navController.popBackStack()
                    }
                }
            }
        }

        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            routes.forEach { item ->
                val argsNameList: List<String> = item.key.getArgs()// + item.key.getParams()
                composable(
                    route = item.key,
                    arguments = argsNameList.map { name ->
                        navArgument(name) { type = NavType.StringType }
                    }
                ) { entry ->
                    val navBarController: NavBarController = remember { AndroidNavBarController() }
                    val navBarFactory: NavBarFactory = remember { AndroidNavBarFactory() }

                    CompositionLocalProvider(LocalNavBarController provides navBarController) {
                        Scaffold(
                            topBar = {
                                if (navBarController.isNavigationBarVisible.not()) return@Scaffold

                                val data: NavBarData? by (navBarController as AndroidNavBarController).navBarDataHolder.collectAsState()
                                navBarFactory.RenderToolbar(
                                    navController = navController,
                                    data = data
                                )
                            }
                        ) { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
                                val argsMap = mutableMapOf<String, String>()

                                argsNameList.forEach {
                                    val accountType =
                                        if (argsNameList.isEmpty()) null else entry.arguments?.getString(
                                            it
                                        )
                                    if (accountType != null) {
                                        argsMap[it] = accountType
                                    }
                                }

                                val tmp = entry.arguments?.toString()?.substringBefore('}')
                                val params = tmp?.substringAfter('?')
                                params?.split('&')?.forEach {
                                    argsMap[it.substringBefore('=')] = it.substringAfter('=')
                                }
                                item.value(provider, nav, argsMap)
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        operator fun invoke(
            startDestination: String,
            block: NavigationFlatScope.() -> Unit
        ): FlatNavigation {
            val routes: MutableMap<String, FlatRouteData> = mutableMapOf()
            RoutesRegister(routes).block()

            return FlatNavigation(
                startDestination = startDestination,
                routes = routes
            )
        }
    }

    private class RoutesRegister(
        private val routes: MutableMap<String, FlatRouteData>
    ) : NavigationFlatScope {
        override fun registerNavigation(
            uri: String,
            childNavigation: (HistoryNavigator, Map<String, String>) -> NavigationHost
        ) {
            routes[uri] = @Composable { provider, navigator, args ->
                childNavigation(navigator, args).Render(provider)
            }
        }

        override fun registerScreen(
            uri: String,
            screen: @Composable (HistoryNavigator, Map<String, String>) -> Unit
        ) {
            routes[uri] = @Composable { provider, navigator, args ->
                val owner: ViewModelStoreOwner = requireNotNull(LocalViewModelStoreOwner.current)
                val controller: NavBarController = LocalNavBarController.current
                RedwoodContent(provider = provider) {
                    CompositionLocalProvider(
                        LocalViewModelStoreOwner provides owner,
                        LocalNavBarController provides controller
                    ) {
                        screen(navigator, args)
                    }
                }
            }
        }
    }

    private class AndroidNavBarController : NavBarController {
        val isNavigationBarVisibleHolder: MutableStateFlow<Boolean> = MutableStateFlow(true)
        val navBarDataHolder: MutableStateFlow<NavBarData?> = MutableStateFlow(null)

        override var isNavigationBarVisible: Boolean
            get() = isNavigationBarVisibleHolder.value
            set(value) {
                isNavigationBarVisibleHolder.value = value
            }

        override var navBarData: NavBarData?
            get() = navBarDataHolder.value
            set(value) {
                navBarDataHolder.value = value
            }
    }
}
