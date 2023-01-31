package ru.alex009.redwoodapp.navigation

import androidx.compose.runtime.Composable
import app.cash.redwood.widget.Widget
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import ru.alex009.redwoodapp.ComposeViewController
import ru.alex009.redwoodapp.ViewModelOwner

data class TabRouteData(
    val title: StringDesc?,
    val icon: ImageResource?,
    val createViewController: (Widget.Provider<UIView>, Navigator) -> UIViewController
)

actual fun navigationTabs(
    startDestination: String,
    block: TabNavigationDsl.() -> Unit
): NavigationHost {
    val routes: MutableMap<String, TabRouteData> = mutableMapOf()
    val viewModelOwners: MutableMap<String, ViewModelOwner> = mutableMapOf()

    val dsl = object : TabNavigationDsl {
        override fun registerScreen(
            uri: String,
            title: StringDesc?,
            icon: ImageResource?,
            screen: @Composable (Navigator) -> Unit
        ) {
            viewModelOwners[uri] = ViewModelOwner(mutableMapOf())
            routes[uri] = TabRouteData(
                title = title,
                icon = icon,
                createViewController = { provider, navigator ->
                    ComposeViewController(
                        provider,
                        false,
                        viewModelOwners[uri]!!
                    ) @Composable {
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
                createViewController = { provider, navigator ->
                    childNavigation(navigator).createViewController(provider)
                }
            )
        }
    }
    dsl.block()
    return TabNavigation(startDestination, routes)
}
