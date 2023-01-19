package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.widget.Widget

actual class NavigationRoot(routes: MutableMap<String, @Composable (Navigator) -> Unit>) {
    @Composable
    fun render(provider: Widget.Provider<@Composable () -> Unit>) {
        RedwoodContent(provider) {
            // android navigation componnent
        }
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
    return NavigationRoot(routes)
}
