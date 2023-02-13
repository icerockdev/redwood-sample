/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation

import app.cash.redwood.widget.Widget
import platform.UIKit.UIView
import platform.UIKit.UIViewController

actual interface NavigationHost {
    fun createViewController(provider: Widget.Provider<UIView>): UIViewController
}

actual interface NavigationRoot {
    fun setViewController(viewController: UIViewController)
}
