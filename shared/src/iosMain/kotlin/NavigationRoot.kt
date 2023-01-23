package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import platform.UIKit.UINavigationController

actual class NavigationRoot : UINavigationController(nibName = null, bundle = null) {

    fun setup(
        routes: MutableMap<String, @Composable (Navigator) -> Unit>
    ) {
      //
    }
}

actual fun navigation(block: NavigationDsl.() -> Unit): NavigationRoot {
    val routes: MutableMap<String, @Composable (Navigator) -> Unit> =
        mutableMapOf<String, @Composable (Navigator) -> Unit>()
    val dsl = object : NavigationDsl {
        override fun register(uri: String, screen: @Composable (Navigator) -> Unit) {
            routes[uri] = screen
        }
    }
    dsl.block()
    return NavigationRoot().apply { setup(routes) }
}


