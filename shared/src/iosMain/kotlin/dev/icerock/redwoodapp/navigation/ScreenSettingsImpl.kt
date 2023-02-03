package dev.icerock.redwoodapp.dev.icerock.redwoodapp.navigation

import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.redwoodapp.navigation.FlatNavigationFactory
import dev.icerock.redwoodapp.navigation.NavigationBar
import platform.UIKit.UINavigationController
import dev.icerock.redwoodapp.navigation.ScreenSettings

class ScreenSettingsImpl<T>(val flatNavigationFactory: FlatNavigationFactory<T>) : ScreenSettings<T> {

    private var navController: UINavigationController? = null
    fun init(navController: UINavigationController) {
        this.navController = navController
    }

    override fun setToolbarData(title: T) {
        navController?.let {
            flatNavigationFactory.render(it, title)
        }
    }
}