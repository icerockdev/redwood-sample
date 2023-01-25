package ru.alex009.redwoodapp.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.cash.redwood.widget.Widget

data class FlatNavigation(
    val startDestination: String,
    val routes: MutableMap<String, FlatRouteData>
) : NavigationHost {

    @Composable
    override fun Render(provider: Widget.Provider<() -> Unit>) {
        val navController: NavHostController = rememberNavController()
        val nav: Navigator = remember(navController) {
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
                composable(route = item.key) { entry ->
                    // TODO implement args
                    val args: Bundle? = entry.arguments
//                    val strArgs: Map<String, String> = args?.keySet().orEmpty()
//                        .associateWith { args!!.getString(it)!! }

                    item.value(provider, nav, emptyMap())
                }
            }
        }
    }
}
