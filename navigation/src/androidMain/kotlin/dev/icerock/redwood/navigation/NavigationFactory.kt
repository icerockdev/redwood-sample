/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation

import dev.icerock.redwood.navigation.scope.NavigationFlatScope
import dev.icerock.redwood.navigation.scope.NavigationScope
import dev.icerock.redwood.navigation.scope.NavigationTabsScope

actual interface NavigationFactory {
    fun createRootNavigation(
        startDestination: String,
        block: NavigationScope.() -> Unit
    ): NavigationHost

    fun createFlatNavigation(
        startDestination: String,
        block: NavigationFlatScope.() -> Unit,
    ): NavigationHost

    fun createTabsNavigation(
        startDestination: String,
        block: NavigationTabsScope.() -> Unit
    ): NavigationHost
}
