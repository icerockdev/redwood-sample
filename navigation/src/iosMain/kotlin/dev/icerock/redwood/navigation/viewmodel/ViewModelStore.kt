/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlin.reflect.KClass

class ViewModelStore {
    private val viewModels: MutableList<ViewModel> = mutableListOf()

    fun <VM : ViewModel> getViewModel(kClass: KClass<VM>, factory: () -> VM): VM {
        @Suppress("UNCHECKED_CAST")
        val storedInstance: VM? = viewModels.firstOrNull { it::class == kClass } as VM?
        if (storedInstance != null) return storedInstance

        val newInstance: VM = factory()
        viewModels.add(newInstance)
        return newInstance
    }

    fun clear() {
        viewModels.forEach { it.onCleared() }
        viewModels.clear()
    }
}
