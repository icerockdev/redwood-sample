package ru.alex009.redwoodapp.ru.alex009.redwoodapp.navigation

import dev.icerock.moko.resources.desc.StringDesc
import platform.UIKit.UINavigationController
import ru.alex009.redwoodapp.navigation.ScreenSettings

class ScreenSettingsImpl(): ScreenSettings {

    private var navController: UINavigationController? = null

    fun init(navController: UINavigationController){
        this.navController = navController
    }

    override fun setTitle(title: StringDesc) {
        navController?.navigationBar?.topItem?.title = title.localized()
    }
}