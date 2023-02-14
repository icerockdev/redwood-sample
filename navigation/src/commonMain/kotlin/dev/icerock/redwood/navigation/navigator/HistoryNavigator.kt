/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.navigator

interface HistoryNavigator : Navigator {
    fun popBackStack()

    fun popToRoot()
}
