/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwoodapp.android.navigation

import dev.icerock.redwood.navigation.NavigationFactory
import dev.icerock.redwood.navigation.NavigationHost
import dev.icerock.redwood.navigation.scope.NavigationFactoryScope
import dev.icerock.redwood.navigation.scope.NavigationFlatScope
import dev.icerock.redwood.navigation.scope.NavigationScope
import dev.icerock.redwood.navigation.scope.NavigationTabsScope

class AndroidNavigationFactory : NavigationFactory {
    override fun createRootNavigation(
        startDestination: String,
        block: NavigationScope.() -> Unit
    ): NavigationHost {
        return RootNavigation(
            navigationFactoryScope = Scope(),
            startDestination = startDestination,
            block = block
        )
    }

    override fun createFlatNavigation(
        startDestination: String,
        block: NavigationFlatScope.() -> Unit
    ): NavigationHost {
        return FlatNavigation(startDestination, block)
    }

    override fun createTabsNavigation(
        startDestination: String,
        block: NavigationTabsScope.() -> Unit
    ): NavigationHost {
        return TabNavigation(startDestination, block)
    }

    inner class Scope : NavigationFactoryScope {
        override fun navigationFlat(
            startDestination: String,
            block: NavigationFlatScope.() -> Unit
        ): NavigationHost {
            return createFlatNavigation(startDestination, block)
        }

        override fun navigationTabs(
            startDestination: String,
            block: NavigationTabsScope.() -> Unit
        ): NavigationHost {
            return createTabsNavigation(startDestination, block)
        }
    }
}
