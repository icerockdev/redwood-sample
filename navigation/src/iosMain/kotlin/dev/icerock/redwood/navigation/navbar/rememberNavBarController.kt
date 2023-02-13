/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.navbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
@ReadOnlyComposable
actual fun rememberNavBarController(): NavBarController {
    return LocalNavBarController.current
}

val LocalNavBarController = staticCompositionLocalOf<NavBarController> {
    error("LocalNavBarController not provided")
}
