package ru.alex009.redwoodapp.navigation

import app.cash.redwood.widget.Widget
import platform.UIKit.UINavigationController
import platform.UIKit.UIView
import platform.UIKit.UIViewController

data class FlatNavigation(
    val startDestination: String,
    val routes: MutableMap<String, FlatRouteData>
) : NavigationHost {

    override fun createViewController(provider: Widget.Provider<UIView>): UIViewController {
        lateinit var navController: UINavigationController
        val navigator: Navigator = object : Navigator {
            override fun navigate(uri: String) {
                val newViewController: UIViewController = routes[uri]!!(provider, this, emptyMap())
                navController.pushViewController(newViewController, animated = true)
            }

            override fun popBackStack() {
                navController.popViewControllerAnimated(true)
            }
        }
        val rootViewController: UIViewController =
            routes[startDestination]!!(provider, navigator, emptyMap())
        navController = UINavigationController(rootViewController)
        return navController
    }
}
