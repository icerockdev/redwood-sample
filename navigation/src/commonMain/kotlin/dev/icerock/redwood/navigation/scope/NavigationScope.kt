/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.scope

import androidx.compose.runtime.Composable
import dev.icerock.redwood.navigation.NavigationHost
import dev.icerock.redwood.navigation.navigator.Navigator

interface NavigationScope {
    fun registerNavigation(
        uri: String,
        childNavigation: NavigationFactoryScope.(Navigator, Map<String, String>) -> NavigationHost
    )

    fun registerScreen(
        uri: String,
        screen: @Composable (Navigator, Map<String, String>) -> Unit
    )
}
