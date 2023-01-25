package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.ImageResource
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory


expect sealed class NavigationRoot {
    class NavigationSimple : NavigationRoot
    class NavigationTabs : NavigationRoot
    class Simple : NavigationRoot
    class SimpleWithArgs<T>: NavigationRoot
}

expect class WidgetType

interface Navigator {
    fun navigate(uri: String)

    fun <T> navigate(uri: String, args:T)
    fun popBackStack()
}

interface NavigationDsl {
    fun <T> register(uri: String, screen: @Composable (Navigator, T?) -> Unit)
    fun register(uri: String, screen: @Composable (Navigator) -> Unit)
    fun register(uri: String, navigationRoot: NavigationRoot)
}

interface TabNavigationDsl {
    fun register(
        uri: String,
        title: String? = null,
        icon: ImageResource? = null,
        screen: @Composable (Navigator) -> Unit
    )

    fun register(
        uri: String,
        title: String? = null,
        icon: ImageResource? = null,
        navigationRoot: NavigationRoot
    )
}

expect fun navigation(
    widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>,
    startDestination: String,
    block: NavigationDsl.() -> Unit
): NavigationRoot

expect fun navigationTabs(
    widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>,
    startDestination: String,
    block: TabNavigationDsl.() -> Unit
): NavigationRoot
