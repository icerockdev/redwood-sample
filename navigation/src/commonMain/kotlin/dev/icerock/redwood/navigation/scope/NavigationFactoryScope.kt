/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.scope

import dev.icerock.redwood.navigation.NavigationHost

interface NavigationFactoryScope {
    fun navigationFlat(
        startDestination: String,
        block: NavigationFlatScope.() -> Unit,
    ): NavigationHost

    fun navigationTabs(
        startDestination: String,
        block: NavigationTabsScope.() -> Unit
    ): NavigationHost
}
