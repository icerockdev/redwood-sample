/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation

import dev.icerock.redwood.navigation.scope.NavigationScope

actual fun navigation(
    navigationFactory: NavigationFactory,
    startDestination: String,
    block: NavigationScope.() -> Unit
): NavigationHost {
    TODO("Not yet implemented")
}