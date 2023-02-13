/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.viewmodel

import kotlin.reflect.KClass

class ViewModelStore {
    private val viewModels: MutableList<Any> = mutableListOf()

    fun <VM : Any> getViewModel(kClass: KClass<VM>, factory: () -> VM): VM {
        @Suppress("UNCHECKED_CAST")
        val storedInstance: VM? = viewModels.firstOrNull { it::class == kClass } as VM?
        if (storedInstance != null) return storedInstance

        val newInstance: VM = factory()
        viewModels.add(newInstance)
        return newInstance
    }
}
