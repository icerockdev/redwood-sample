package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import platform.UIKit.UINavigationController

actual class NavigationRoot : UINavigationController(nibName = null, bundle = null) {

    fun setup(
        routes: MutableMap<String, @Composable (Navigator) -> Unit>
    ) {
//        pushViewController(RedwoodViewController(r))
    }
}

actual fun navigation(startDestination: String, block: NavigationDsl.() -> Unit): NavigationRoot {
    TODO("Not yet implemented")
}

actual fun navigationTabs(startDestination: String, block: NavigationDsl.() -> Unit): NavigationRoot {
    TODO("Not yet implemented")
}
