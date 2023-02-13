/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation

import app.cash.redwood.widget.Widget
import platform.UIKit.UIStackView
import platform.UIKit.UIView
import platform.UIKit.UIViewController

actual interface NavigationFactory {
    val provider: Widget.Provider<UIView>

    fun createRootNavigation(): NavigationRoot

//    fun createFlatNavigation(
//        startDestination: String,
//        block: (NavigationFlatScope) -> Unit,
//    ): NavigationHost
//
//    fun createTabsNavigation(
//        startDestination: String,
//        block: (NavigationTabsScope) -> Unit
//    ): NavigationHost

    fun createComposeScreen(
        delegate: (UIStackView) -> RedwoodViewControllerDelegate
    ): UIViewController
}
