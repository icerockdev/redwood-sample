@file:OptIn(ExperimentalUnitApi::class)

package dev.icerock.redwoodapp.navigation

import androidx.compose.ui.unit.ExperimentalUnitApi
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.Flow

data class NavBarAction(
    val icon: ImageResource?,
    val iconFlow: Flow<ImageResource>?,
    val badge: Flow<StringDesc>?,
    val onClick: () -> Unit
)