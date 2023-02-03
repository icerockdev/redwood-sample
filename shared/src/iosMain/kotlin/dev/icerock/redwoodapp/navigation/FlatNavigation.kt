package dev.icerock.redwoodapp.navigation

import app.cash.redwood.widget.Widget
import platform.UIKit.UIColor
import platform.UIKit.UINavigationController
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import platform.UIKit.backgroundColor
import dev.icerock.redwoodapp.ViewModelOwner
import dev.icerock.redwoodapp.dev.icerock.redwoodapp.navigation.ScreenSettingsImpl
import dev.icerock.redwoodapp.dev.icerock.redwoodapp.navigation.getParams
import dev.icerock.redwoodapp.dev.icerock.redwoodapp.navigation.getStableParts
import dev.icerock.redwoodapp.dev.icerock.redwoodapp.navigation.isPlaceholderOf


data class FlatNavigation<T>(
    val startDestination: String,
    val routes: MutableMap<String, FlatRouteData>,
    val viewModelOwners: MutableMap<String, ViewModelOwner>,
    val screenSettings: ScreenSettingsImpl<T>
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
            }

            override fun popBackStack() {
                navController.popViewControllerAnimated(false)
            }
        }
        val rootViewController: UIViewController =
            routes[startDestination]!!(provider, navigator, emptyMap(), viewModelOwners[startDestination]!!)
        navController = UINavigationController(rootViewController)
        screenSettings.init(navController)
        return navController
    }
}

actual interface FlatNavigationFactory<T>{

    fun render(navControler: UINavigationController, data: T)
}