package dev.icerock.redwoodapp.dev.icerock.redwoodapp.navigation

import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.redwoodapp.navigation.NavigationBar
import platform.UIKit.UINavigationController
import dev.icerock.redwoodapp.navigation.ScreenSettings

class ScreenSettingsImpl() : ScreenSettings {

    private var navController: UINavigationController? = null
    fun init(navController: UINavigationController) {
        this.navController = navController
    }

    override fun setTitle(title: StringDesc) {
        navController?.navigationBar?.topItem?.title = title.localized()
    }

    override fun setNavigationBar(navigationBar: NavigationBar) {
        navController?.navigationBar
    }
}