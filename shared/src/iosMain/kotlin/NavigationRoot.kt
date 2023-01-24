package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import platform.UIKit.UINavigationController
import platform.UIKit.UIViewController
import platform.UIKit.UIView
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory

actual class NavigationRoot(val widgetFactory: RedwoodAppSchemaWidgetFactory<UIView>) :
    UINavigationController(nibName = null, bundle = null) {

    val navigator = object : Navigator {
        override fun navigate(uri: String) {
            navigationMap.get(uri)?.let {
                pushViewController(it, true)
            }
        }

        override fun popBackStack() {
            popViewControllerAnimated(true)
        }
    }

    val navigationMap: MutableMap<String, UIViewController> = mutableMapOf()
    fun setup(
        routes: MutableMap<String, @Composable (Navigator) -> Unit>
    ) {
        routes.forEach { entry ->
            navigationMap.put(entry.key, ComposeViewController(entry.value, widgetFactory))
        }
    }
}

actual fun navigation(
    widgetFactory: RedwoodAppSchemaWidgetFactory<UIView>,
    block: NavigationDsl.() -> Unit,
): NavigationRoot {
    val routes: MutableMap<String, @Composable (Navigator) -> Unit> =
        mutableMapOf<String, @Composable (Navigator) -> Unit>()
    val dsl = object : NavigationDsl {
        override fun register(uri: String, screen: @Composable (Navigator) -> Unit) {
            routes[uri] = screen
        }
    }
    dsl.block()
    return NavigationRoot(widgetFactory).apply { setup(routes) }
}


