package dev.icerock.redwoodapp.navigation

import androidx.compose.runtime.Composable
import app.cash.redwood.widget.Widget
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import dev.icerock.redwoodapp.ComposeViewController

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
                createViewController = { provider, navigator ->
                    ComposeViewController(provider, false) @Composable {
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
