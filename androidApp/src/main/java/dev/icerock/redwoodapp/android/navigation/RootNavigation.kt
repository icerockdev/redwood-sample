/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwoodapp.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
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
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.navigation.scope.NavigationFactoryScope
import dev.icerock.redwood.navigation.scope.NavigationScope

private typealias RootRouteData = @Composable (Widget.Provider<() -> Unit>, Navigator, Map<String, String>) -> Unit

data class RootNavigation(
    val startDestination: String,
    val routes: Map<String, RootRouteData>,
) : NavigationHost {

    @Composable
    override fun Render(provider: Widget.Provider<() -> Unit>) {
        val navController: NavHostController = rememberNavController()

        val navigator: Navigator = remember(navController) {
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
                val argsNameList: List<String> = item.key.getArgs()// + item.key.getParams()
                composable(
                    route = item.key,
                    arguments = argsNameList.map { name ->
                        navArgument(name) { type = NavType.StringType }
                    }
                ) { entry ->
                    // TODO args parsing seems strange
                    val args: Map<String, String> = argsNameList.associateWith {
                        entry.arguments?.getString(it).orEmpty()
                    }

//                    val tmp = entry.arguments?.toString()?.substringBefore('}')
//                    val params = tmp?.substringAfter('?')
//                    params?.split('&')?.forEach {
//                        argsMap[it.substringBefore('=')] = it.substringAfter('=')
//                    }

                    item.value(provider, navigator, args)
                }
            }
        }
    }

    companion object {
        operator fun invoke(
            navigationFactoryScope: NavigationFactoryScope,
            startDestination: String,
            block: NavigationScope.() -> Unit
        ): RootNavigation {
            val routes: MutableMap<String, RootRouteData> = mutableMapOf()
            RoutesRegister(routes, navigationFactoryScope).block()

            return RootNavigation(
                startDestination = startDestination,
                routes = routes
            )
        }
    }

    private class RoutesRegister(
        private val routes: MutableMap<String, RootRouteData>,
        private val navigationFactoryScope: NavigationFactoryScope
    ) : NavigationScope {
        override fun registerNavigation(
            uri: String,
            childNavigation: NavigationFactoryScope.(Navigator, Map<String, String>) -> NavigationHost
        ) {
            routes[uri] = @Composable { provider, navigator, args ->
                childNavigation(navigationFactoryScope, navigator, args).Render(provider)
            }
        }

        override fun registerScreen(
            uri: String,
            screen: @Composable (Navigator, Map<String, String>) -> Unit
        ) {
            routes[uri] = @Composable { provider, navigator, args ->
                val owner: ViewModelStoreOwner = requireNotNull(LocalViewModelStoreOwner.current)
                RedwoodContent(provider = provider) {
                    CompositionLocalProvider(LocalViewModelStoreOwner provides owner) {
                        screen(navigator, args)
                    }
                }
            }
        }
    }
}
