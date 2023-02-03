package dev.icerock.redwoodapp.navigation

import platform.UIKit.UIViewController

class ScreenSettingsImpl<T>(val flatNavigationFactory: FlatNavigationFactory<T>) : ScreenSettings<T> {

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