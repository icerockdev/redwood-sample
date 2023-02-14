/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.navbar

import androidx.compose.runtime.Composable

interface NavBarData

interface NavBarController {
    var isNavigationBarVisible: Boolean
    var navBarData: NavBarData?
}

@Composable
expect fun rememberNavBarController(): NavBarController
