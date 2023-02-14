/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel

@Composable
actual inline fun <reified VM : ViewModel> getViewModel(
    crossinline factory: () -> VM
): VM {
    return viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return factory() as T
            }
        }
    )
}
