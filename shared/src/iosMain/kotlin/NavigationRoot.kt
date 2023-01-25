package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.ImageResource
import platform.UIKit.UIColor
import platform.UIKit.UINavigationController
import platform.UIKit.UITabBarController
import platform.UIKit.UITabBarItem
import platform.UIKit.UIViewController
import platform.UIKit.UIView
import platform.UIKit.navigationItem
import platform.UIKit.setTabBarItem
import platform.UIKit.tabBarController
import platform.UIKit.tabBarItem
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory


actual sealed class NavigationRoot {
    abstract fun getViewController(
        navigator: Navigator?,
        widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
    ): UIViewController

    actual class NavigationSimple(
        private val startDestination: String,
        private val widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
    ) : NavigationRoot() {
        val viewController = MyNavigationSimple(startDestination, widgetFactory)
        override fun getViewController(
            navigator: Navigator?,
            widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
        ): UIViewController {
            return viewController
        }

    }

    actual class NavigationTabs(
        private val startDestination: String,
        private val widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
    ) : NavigationRoot() {

        val viewController = MyNavigationTab(startDestination, widgetFactory)
        override fun getViewController(
            navigator: Navigator?,
            widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
        ): UIViewController {
            return viewController
        }

    }

    actual class Simple(private val composeFun: @Composable (Navigator) -> Unit) :
        NavigationRoot() {
        override fun getViewController(
            navigator: Navigator?,
            widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
        ): UIViewController {
            // todo fix
            return ComposeViewController(composeFun, widgetFactory, navigator!!)
        }

    }
}

class MyNavigationSimple(
    private val startDestination: String,
    private val widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
) : UINavigationController(nibName = null, bundle = null) {

    val navigator = object : Navigator {
        override fun navigate(uri: String) {
            navigationMap.get(uri)?.let {
                pushViewController(it.invoke(uri), true)
            }
        }

        override fun popBackStack() {
            //do nothing
        }
    }

    val navigationMap: MutableMap<String, (String) -> UIViewController> = mutableMapOf()
    fun setup(
        routes: MutableMap<String, NavigationRoot>
    ) {
        routes.forEach { entry ->
            navigationMap.put(
                entry.key,
                { entry.value.getViewController(navigator, widgetFactory) })
        }
        setViewControllers(
            listOf(
                navigationMap.get(startDestination)!!.invoke(startDestination)
            ),
            false
        )
    }
}

class MyNavigationTab(
    private val startDestination: String,
    private val widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
) : UITabBarController(nibName = null, bundle = null) {

    val navigationMap: MutableMap<String, () -> UIViewController> = mutableMapOf()
    fun setup(
        routes: MutableMap<String, NavigationRoot>,
        icons: MutableMap<String, TabItem>,
    ) {
        routes.forEach { entry ->
            navigationMap.put(
                entry.key,
                { entry.value.getViewController(null, widgetFactory) })
        }
        setViewControllers(navigationMap.map { entity ->
            entity.value.invoke().apply {
                tabBarItem.title = icons.get(entity.key)?.title
                tabBarItem.image = icons.get(entity.key)?.icon?.toUIImage()
            }
        }, false)
        tabBar.barTintColor = UIColor.whiteColor
    }
}

actual typealias WidgetType = UIView

actual fun navigation(
    widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>,
    startDestination: String,
    block: NavigationDsl.() -> Unit
): NavigationRoot {
    val routes: MutableMap<String, NavigationRoot> =
        mutableMapOf<String, NavigationRoot>()
    val dsl = object : NavigationDsl {
        override fun register(uri: String, screen: @Composable (Navigator) -> Unit) {
            routes[uri] = NavigationRoot.Simple(screen)
        }

        override fun register(uri: String, navigationRoot: NavigationRoot) {
            routes[uri] = navigationRoot
        }
    }
    dsl.block()
    return NavigationRoot.NavigationSimple(startDestination, widgetFactory)
        .apply { viewController.setup(routes) }
}

actual fun navigationTabs(
    widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>,
    startDestination: String,
    block: TabNavigationDsl.() -> Unit
): NavigationRoot {
    val routes: MutableMap<String, NavigationRoot> =
        mutableMapOf<String, NavigationRoot>()
    val icons: MutableMap<String, TabItem> =
        mutableMapOf()
    val dsl = object : TabNavigationDsl {

        override fun register(
            uri: String,
            title: String?,
            icon: ImageResource?,
            navigationRoot: NavigationRoot
        ) {
            routes[uri] = navigationRoot
            icons[uri] = TabItem(title, icon)
        }

        override fun register(
            uri: String,
            title: String?,
            icon: ImageResource?,
            screen: @Composable  (Navigator) -> Unit
        ) {
            routes[uri] = NavigationRoot.Simple(screen)
            icons[uri] = TabItem(title, icon)
        }
    }
    dsl.block()
    return NavigationRoot.NavigationTabs(startDestination, widgetFactory)
        .apply { viewController.setup(routes, icons) }
}

data class TabItem(
    val title: String? = null,
    val icon: ImageResource? = null
)