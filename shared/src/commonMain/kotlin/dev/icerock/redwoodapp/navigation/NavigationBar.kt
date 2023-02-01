package dev.icerock.redwoodapp.navigation

import dev.icerock.moko.fields.flow.FormField
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.Flow

expect sealed class NavigationBar {
    class SimpleNavigationBar(block: SimpleNavigationBarDsl.() -> Unit) : NavigationBar
    class SearchNavigationBar(block: SearchNavigationBarDsl.() -> Unit) : NavigationBar
}

interface SimpleNavigationBarDsl {
    fun setTitle(text: StringDesc)
    fun addAction(
        icon: ImageResource,
        badge: Flow<StringDesc>?,
        onClick: () -> Unit
    )
    fun addAction(
        icon: Flow<ImageResource>,
        badge: Flow<StringDesc>?,
        onClick: () -> Unit
    )
}

interface SearchNavigationBarDsl {
    fun setTitle(text: StringDesc)
    fun addAction(
        icon: ImageResource,
        badge: Flow<StringDesc>?,
        onClick: () -> Unit
    )
    fun addAction(
        icon: Flow<ImageResource>,
        badge: Flow<StringDesc>?,
        onClick: () -> Unit
    )
    fun setSearch(formField: FormField<String, StringDesc>)
}