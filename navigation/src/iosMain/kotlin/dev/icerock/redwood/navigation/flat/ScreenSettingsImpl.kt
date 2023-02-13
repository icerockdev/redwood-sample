/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.flat

import platform.UIKit.UIViewController

class ScreenSettingsImpl<T>(
    private val flatNavigationFactory: FlatNavigationFactory<T>
) : ScreenSettings<T> {

    private var viewController: UIViewController? = null
    fun init(viewController: UIViewController) {
        this.viewController = viewController
    }

    override fun setToolbarData(title: T) {
        viewController?.let {
            flatNavigationFactory.render(it, title)
        }
    }
}