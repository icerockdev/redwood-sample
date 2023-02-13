/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwoodapp

import dev.icerock.moko.fields.flow.FormField
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.redwood.navigation.navbar.NavBarData

sealed class ToolbarArgs : NavBarData {
    data class Simple(
        val title: StringDesc,
        val actions: List<ToolbarAction> = listOf()
    ) : ToolbarArgs()

    data class Search(
        val search: FormField<String, StringDesc?>,
        val placeholder: StringDesc?,
        val action: ToolbarAction,
        val leftButton: ToolbarButton?,
        val rightButton: ToolbarButton?,
    ) : ToolbarArgs()

    object NoToolbar : ToolbarArgs()
}

data class ToolbarButton(
    val title: StringDesc,
    val icon: ImageResource,
    val onClick: () -> Unit
)

data class ToolbarAction(
    val icon: ImageResource,
    val badge: StringDesc?,
    val onClick: () -> Unit
)
