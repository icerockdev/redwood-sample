package dev.icerock.redwoodapp.navigation

actual sealed class NavigationBar {
    actual class SimpleNavigationBar actual constructor(block: SimpleNavigationBarDsl.() -> Unit) :
        NavigationBar()

    actual class SearchNavigationBar actual constructor(block: SearchNavigationBarDsl.() -> Unit) :
        NavigationBar()
}