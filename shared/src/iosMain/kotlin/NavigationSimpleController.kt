package ru.alex009.redwoodapp

import platform.UIKit.UINavigationController
import platform.UIKit.UIViewController
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory

class NavigationSimpleController(
    private val startDestination: String,
    private val widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>
) : UINavigationController(nibName = null, bundle = null) {

    val navigator = object : Navigator {
        override fun navigate(uri: String) {
            navigationMap.get(uri)?.let {
                pushViewController(it.invoke(null), false)
            }
        }

        override fun <T> navigate(uri: String, args: T) {
            navigationMap.get(uri)?.let {
                pushViewController(it.invoke(args), false)
            }
        }

        override fun popBackStack() {
            popViewControllerAnimated(false)
        }
    }

    val navigationMap: MutableMap<String, (Any?) -> UIViewController> = mutableMapOf()
    fun setup(
        routes: MutableMap<String, NavigationRoot>
    ) {
        routes.forEach { entry ->
            navigationMap.put(
                entry.key,
                { args -> entry.value.getViewController(navigator, widgetFactory, args) })
        }
        setViewControllers(
            listOf(
                navigationMap.get(startDestination)!!.invoke(startDestination)
            ),
            false
        )
    }
}