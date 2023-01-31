package ru.alex009.redwoodapp.navigation

import app.cash.redwood.widget.Widget
import platform.UIKit.UIColor
import platform.UIKit.UINavigationController
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import platform.UIKit.backgroundColor
import ru.alex009.redwoodapp.ViewModelOwner
import ru.alex009.redwoodapp.ru.alex009.redwoodapp.navigation.ScreenSettingsImpl
import ru.alex009.redwoodapp.ru.alex009.redwoodapp.navigation.getParams
import ru.alex009.redwoodapp.ru.alex009.redwoodapp.navigation.getStableParts
import ru.alex009.redwoodapp.ru.alex009.redwoodapp.navigation.isPlaceholderOf

data class FlatNavigation(
    val startDestination: String,
    val routes: MutableMap<String, FlatRouteData>,
    val viewModelOwners: MutableMap<String, ViewModelOwner>,
    val navBarVisibility: MutableMap<String, Boolean>,
    val screenSettings: ScreenSettingsImpl
) : NavigationHost {

    override fun createViewController(provider: Widget.Provider<UIView>): UIViewController {
        lateinit var navController: UINavigationController

        val navigator: Navigator = object : Navigator {
            override fun navigate(uri: String) {
                val startUri = uri.substringBefore('?')
                val key = routes.keys.find { it.isPlaceholderOf(startUri) } ?: return
                val params = uri.substringAfter('?')
                val paramsMap = mutableMapOf<String, String>()
                params.split('&').forEach {
                    paramsMap[it.substringBefore('=')] = it.substringAfter('=')
                }
                val newViewController: UIViewController =
                    routes[key]!!(
                        provider,
                        this,
                        paramsMap
                            .apply {
                                putAll(startUri.getParams(key, key.getStableParts()))
                            },
                        viewModelOwners[startDestination]!!
                    )
                navController.pushViewController(newViewController, animated = false)
                navController.navigationBarHidden = navBarVisibility[key]?.not() ?: false
            }

            override fun popBackStack() {
                navController.popViewControllerAnimated(false)
            }
        }
        val rootViewController: UIViewController =
            routes[startDestination]!!(provider, navigator, emptyMap(), viewModelOwners[startDestination]!!)
        navController = UINavigationController(rootViewController)
        navController.navigationBarHidden = navBarVisibility[startDestination]?.not() ?: false
        navController.navigationBar.backgroundColor = UIColor.whiteColor
        screenSettings.init(navController)
        return navController
    }
}

