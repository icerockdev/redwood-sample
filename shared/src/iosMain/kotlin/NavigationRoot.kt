package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.ImageResource
import platform.UIKit.UIColor
import platform.UIKit.UITabBarController
import platform.UIKit.UIViewController
import platform.UIKit.UIView
import platform.UIKit.navigationController
import platform.UIKit.tabBarItem
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory


actual sealed class NavigationRoot {
    abstract fun getViewController(
        navigator: Navigator?,
        widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>,
        args: Any? = null,
    ): UIViewController

    actual class NavigationSimple(
        private val startDestination: String,
        private val widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
    ) : NavigationRoot() {
        val viewController =
            NavigationSimpleController(startDestination, widgetFactory)
        override fun getViewController(
            navigator: Navigator?,
            widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>,
            args: Any?,
        ): UIViewController {
            return viewController
        }

    }

    actual class NavigationTabs(
        private val startDestination: String,
        private val widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
    ) : NavigationRoot() {

        val viewController = NavigationTabController(startDestination, widgetFactory)
        override fun getViewController(
            navigator: Navigator?,
            widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>,
            args: Any?,
        ): UIViewController {
            return viewController
        }

    }

    actual class Simple(private val composeFun: @Composable (Navigator) -> Unit) :
        NavigationRoot() {
        override fun getViewController(
            navigator: Navigator?,
            widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>,
            args: Any?,
        ): UIViewController {
            // todo fix
            return ComposeViewController(composeFun, widgetFactory, navigator!!)
        }
    }

    // todo remove
    actual class SimpleWithArgs<T>(
        private val composeFun: @Composable (Navigator, T?) -> Unit
    ) : NavigationRoot() {
        override fun getViewController(
            navigator: Navigator?,
            widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>,
            args: Any?,
        ): UIViewController {
            // todo fix this
            val argsT = (args as? T)?:(if(args == null) "oh no" else "test") as? T
            val test: @Composable (Navigator) -> Unit = { composeFun(it, argsT) }
            return ComposeViewController(test, widgetFactory, navigator!!)
        }

         fun getArgsViewController(
            navigator: Navigator?,
            widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>,
            args: T?,
        ): UIViewController {
            val test: @Composable (Navigator) -> Unit = { composeFun(it, args) }
            return ComposeViewController(test, widgetFactory, navigator!!)
        }
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

        override fun <T> register(
            uri: String,
            screen: @Composable (Navigator, T?) -> Unit
        ) {
            routes[uri] = NavigationRoot.SimpleWithArgs<T>(screen)
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
            screen: @Composable (Navigator) -> Unit
        ) {
            routes[uri] = NavigationRoot.Simple(screen)
            icons[uri] = TabItem(title, icon)
        }
    }
    dsl.block()
    return NavigationRoot.NavigationTabs(startDestination, widgetFactory)
        .apply { viewController.setup(routes, icons) }
}

