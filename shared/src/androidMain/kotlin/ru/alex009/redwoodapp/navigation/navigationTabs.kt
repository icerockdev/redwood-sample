@file:JvmName("TabNavigationKt")

package ru.alex009.redwoodapp.navigation

import androidx.compose.runtime.Composable
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.widget.Widget
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc

data class TabRouteData(
    val title: StringDesc?,
    val icon: ImageResource?,
    val render: @Composable (Widget.Provider<() -> Unit>, Navigator) -> Unit
)

actual fun navigationTabs(
    startDestination: String,
    block: TabNavigationDsl.() -> Unit
): NavigationHost {
    val routes: MutableMap<String, TabRouteData> = mutableMapOf()
    val dsl = object : TabNavigationDsl {
        override fun registerScreen(
            uri: String,
            title: StringDesc?,
            icon: ImageResource?,
            screen: @Composable (Navigator) -> Unit
        ) {
            routes[uri] = TabRouteData(
                title = title,
                icon = icon,
                render = @Composable { provider, navigator ->
                    RedwoodContent(provider) {
                        screen(navigator)
                    }
                }
            )
        }

        override fun registerNavigation(
            uri: String,
            title: StringDesc?,
            icon: ImageResource?,
            childNavigation: (Navigator) -> NavigationHost
        ) {
            routes[uri] = TabRouteData(
                title = title,
                icon = icon,
                render = @Composable { provider, navigator ->
                    childNavigation(navigator).Render(provider)
                }
            )
        }
    }
    dsl.block()
    return TabNavigation(startDestination, routes)
}
