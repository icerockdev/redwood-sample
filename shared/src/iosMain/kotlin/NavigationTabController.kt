package ru.alex009.redwoodapp

import dev.icerock.moko.resources.ImageResource
import platform.UIKit.UIColor
import platform.UIKit.UITabBarController
import platform.UIKit.UIViewController
import platform.UIKit.navigationController
import platform.UIKit.tabBarItem
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory

class NavigationTabController(
    private val startDestination: String,
    private val widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
) : UITabBarController(nibName = null, bundle = null) {
    // todo remove?
    val navigator = object : Navigator {
        override fun navigate(uri: String) {
            // do nothing
        }

        override fun <T> navigate(uri: String, args: T) {
            // do nothing
        }

        override fun popBackStack() {
            // do nothing
        }
    }
    val navigationMap: MutableMap<String, () -> UIViewController> = mutableMapOf()
    fun setup(
        routes: MutableMap<String, NavigationRoot>,
        icons: MutableMap<String, TabItem>,
    ) {
        routes.forEach { entry ->
            navigationMap.put(
                entry.key,
                { entry.value.getViewController(navigator, widgetFactory) })
        }
        setViewControllers(navigationMap.map { entity ->
            entity.value.invoke().apply {
                tabBarItem.title = icons.get(entity.key)?.title
                tabBarItem.image = icons.get(entity.key)?.icon?.toUIImage()
            }
        }, false)
        tabBar.barTintColor = UIColor.grayColor
        navigationController?.navigationBarHidden = true
    }
}

data class TabItem(
    val title: String? = null,
    val icon: ImageResource? = null
)