/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel

expect inline fun <reified VM : ViewModel> getViewModel(
    crossinline factory: () -> VM
): VM
