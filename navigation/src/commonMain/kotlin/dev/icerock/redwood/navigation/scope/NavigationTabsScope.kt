/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.scope

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.redwood.navigation.NavigationHost
import dev.icerock.redwood.navigation.navigator.Navigator

interface NavigationTabsScope {
    fun registerScreen(
        uri: String,
        title: StringDesc? = null,
        icon: ImageResource? = null,
        screen: @Composable (Navigator) -> Unit
    )

    fun registerNavigation(
        uri: String,
        title: StringDesc? = null,
        icon: ImageResource? = null,
        childNavigation: (Navigator) -> NavigationHost
    )
}
