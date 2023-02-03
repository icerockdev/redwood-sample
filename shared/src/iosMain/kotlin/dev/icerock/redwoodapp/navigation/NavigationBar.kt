package dev.icerock.redwoodapp.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import platform.CoreGraphics.CGFloat
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectMake
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIAction
import platform.UIKit.UIAction.Companion.actionWithHandler
import platform.UIKit.UIBarButtonItem
import platform.UIKit.UIBarButtonItemStyle
import platform.UIKit.UIColor
import platform.UIKit.UIFont
import platform.UIKit.UIImage
import platform.UIKit.UILabel
import platform.UIKit.UINavigationBarAppearance
import platform.UIKit.UINavigationController
import platform.UIKit.UITapGestureRecognizer
import platform.UIKit.UIView
import platform.UIKit.addGestureRecognizer
import platform.UIKit.addSubview
import platform.UIKit.backgroundColor
import platform.UIKit.clipsToBounds

actual sealed class NavigationBar {

    abstract fun render(navController: UINavigationController)
    actual class SimpleNavigationBar actual constructor(block: SimpleNavigationBarDsl.() -> Unit) :
        NavigationBar() {

        // todo fix to viewModelScoup?
        val coroutineScope = CoroutineScope(Job() + Dispatchers.Default)

        val menuStateFlow = MutableStateFlow<List<NavBarAction>>(listOf())
        var title by mutableStateOf<StringDesc>("".desc())
        var actions by mutableStateOf<List<NavBarAction>>(listOf())

        val dsl = object : SimpleNavigationBarDsl {
            override fun setTitle(text: StringDesc) {
                title = text
            }

            override fun addAction(
                icon: ImageResource,
                badge: Flow<StringDesc>?,
                onClick: () -> Unit
            ) {
                actions = actions.plus(
                    NavBarAction(
                        icon,
                        null,
                        badge,
                        null,
                        onClick
                    )
                )
            }

            override fun addAction(
                icon: Flow<ImageResource>,
                badge: Flow<StringDesc>?,
                onClick: () -> Unit
            ) {
                actions = actions.plus(
                    NavBarAction(
                        null,
                        icon,
                        badge,
                        null,
                        onClick
                    )
                )
            }

        }

        init {
            dsl.block()
        }

        override fun render(navController: UINavigationController) {
            navController.navigationBar.topItem?.title = title.localized()

            actions.forEachIndexed { actionIndex, navAction ->
                if (navAction.iconFlos != null) {
                    coroutineScope.launch {
                        navAction.iconFlos.collect { newImage ->
                            menuStateFlow.value = menuStateFlow.value.mapIndexed { index, it ->
                                if (index == actionIndex) {
                                    it.copy(icons = newImage)
                                } else {
                                    it
                                }
                            }
                        }
                    }
                }
                if (navAction.badge != null) {
                    coroutineScope.launch {
                        navAction.badge.collect { newImage ->
                            menuStateFlow.value = menuStateFlow.value.mapIndexed { index, it ->
                                if (index == actionIndex) {
                                    it.copy(badgeText = newImage.localized())
                                } else {
                                    it
                                }
                            }
                        }
                    }
                }
            }
            menuStateFlow.value = actions
            coroutineScope.launch {
                menuStateFlow.collect() {
                    val barButtonMenu = it.map { barAction ->

                        UIBarButtonItem(
                            image = barAction.icons?.let {
                                UIImage.imageNamed(it.assetImageName)
                            },
                            null
                        ).apply {
                            setPrimaryAction(actionWithHandler {
                                barAction.onClick.invoke()
                            })
                            setImage(barAction.icons?.let {
                                UIImage.imageNamed(it.assetImageName)
                            })
                            setTarget(this)
                        }
                    }
                    withContext(Dispatchers.Main) {
                        navController.navigationBar.topItem?.rightBarButtonItems =
                            barButtonMenu.reversed()
                    }
                }
            }


        }
    }

    actual class SearchNavigationBar actual constructor(block: SearchNavigationBarDsl.() -> Unit) :
        NavigationBar() {
        override fun render(navController: UINavigationController) {
            // do something
        }
    }
}

data class NavBarAction(
    val icons: ImageResource?,
    val iconFlos: Flow<ImageResource>?,
    val badge: Flow<StringDesc>?,
    val badgeText: String?,
    val onClick: () -> Unit
) {
}