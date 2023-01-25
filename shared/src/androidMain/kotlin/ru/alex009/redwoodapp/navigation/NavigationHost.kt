package ru.alex009.redwoodapp.navigation

import androidx.compose.runtime.Composable
import app.cash.redwood.widget.Widget

actual interface NavigationHost {

    @Composable
    fun Render(provider: Widget.Provider<@Composable () -> Unit>)
}
