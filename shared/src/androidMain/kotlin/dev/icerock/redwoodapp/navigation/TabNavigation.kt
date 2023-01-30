package dev.icerock.redwoodapp.navigation

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.cash.redwood.widget.Widget
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc

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
                            BottomBarNavigation(navController)
                        },
                        content = { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
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

            routes.forEach { (key, data) ->
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
            selectedContentColor = Color.Black,
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
}
