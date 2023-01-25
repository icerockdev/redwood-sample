package ru.alex009.redwoodapp.navigation

import app.cash.redwood.widget.Widget
import platform.UIKit.UIView
import platform.UIKit.UIViewController

actual interface NavigationHost {

    fun createViewController(provider: Widget.Provider<UIView>): UIViewController
}
