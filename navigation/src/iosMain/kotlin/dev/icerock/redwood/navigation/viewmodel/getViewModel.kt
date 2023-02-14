/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import dev.icerock.moko.mvvm.viewmodel.ViewModel

@Composable
actual inline fun <reified VM : ViewModel> getViewModel(
    crossinline factory: () -> VM
): VM {
    val store: ViewModelStore = LocalViewModelStore.current
    return getViewModel(store, factory)
}

inline fun <reified VM : ViewModel> getViewModel(
    store: ViewModelStore,
    crossinline factory: () -> VM
): VM {
    return store.getViewModel(VM::class) {
        factory()
    }
}

val LocalViewModelStore = staticCompositionLocalOf<ViewModelStore> {
    error("LocalViewModelStore not provided")
}
