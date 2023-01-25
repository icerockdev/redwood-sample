package ru.alex009.redwoodapp.navigation

import app.cash.redwood.widget.Widget
import platform.UIKit.UITabBarController
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import platform.UIKit.tabBarItem

data class TabNavigation(
    val startDestination: String,
    val routes: Map<String, TabRouteData>
) : NavigationHost {

    override fun createViewController(provider: Widget.Provider<UIView>): UIViewController {
        @Suppress("JoinDeclarationAndAssignment")
        lateinit var tabBarController: UITabBarController
        val navigator: Navigator = object : Navigator {
            override fun navigate(uri: String) {
                val idx: Int = routes.keys.indexOfFirst { it == uri }
                tabBarController.setSelectedIndex(idx.toULong())
            }

            override fun popBackStack() {
                // do nothing now
            }
        }
        val tabsViewControllers: List<UIViewController> = routes.map { (key, value) ->
            val vc: UIViewController = value.createViewController(provider, navigator)
            vc.tabBarItem.title = value.title?.localized()
            vc.tabBarItem.image = value.icon?.toUIImage()
            vc
        }
        tabBarController = UITabBarController()
        tabBarController.setViewControllers(tabsViewControllers, animated = false)
        return tabBarController
    }
}
