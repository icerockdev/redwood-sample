package dev.icerock.redwoodapp.navigation

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc

interface TabNavigationDsl {
    fun registerScreen(
        uri: String,
        title: StringDesc? = null,
        icon: ImageResource? = null,
        screen: @Composable (Navigator) -> Unit
    )

    fun registerNavigation(
        uri: String,
        title: StringDesc? = null,
        icon: ImageResource? = null,
        childNavigation: (Navigator) -> NavigationHost
    )
}

expect fun navigationTabs(
    startDestination: String,
    block: TabNavigationDsl.() -> Unit
): NavigationHost
