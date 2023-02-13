/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation

import androidx.compose.runtime.Composable
import app.cash.redwood.widget.Widget

actual interface NavigationHost {
    @Composable
    fun Render(provider: Widget.Provider<@Composable () -> Unit>)
}
