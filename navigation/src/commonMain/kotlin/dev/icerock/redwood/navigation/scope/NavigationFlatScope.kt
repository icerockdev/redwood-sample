/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.scope

import androidx.compose.runtime.Composable
import dev.icerock.redwood.navigation.navigator.HistoryNavigator
import dev.icerock.redwood.navigation.NavigationHost

interface NavigationFlatScope {
    fun registerScreen(
        uri: String,
        screen: @Composable (
            HistoryNavigator,
            Map<String, String>
        ) -> Unit
    )

    fun registerNavigation(
        uri: String,
        childNavigation: (
            HistoryNavigator,
            Map<String, String>
        ) -> NavigationHost
    )
}
