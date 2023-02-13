/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.remember
import app.cash.redwood.compose.RedwoodComposition
import app.cash.redwood.widget.UIViewChildren
import app.cash.redwood.widget.Widget
import dev.icerock.redwood.navigation.viewmodel.LocalViewModelStore
import dev.icerock.redwood.navigation.viewmodel.ViewModelStore
import kotlinx.cinterop.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.plus
import platform.UIKit.UIStackView
import platform.UIKit.UIView

@OptIn(InternalComposeApi::class)
class RedwoodViewControllerDelegateImpl internal constructor(
    root: UIStackView,
    compose: @Composable () -> Unit,
    provider: Widget.Provider<UIView>
) : RedwoodViewControllerDelegate {
    private val clock = BroadcastFrameClock()
    private val scope: CoroutineScope = MainScope() + clock
    private val store: ViewModelStore = ViewModelStore()

    init {
        val children = UIViewChildren(
            parent = root,
            insert = { view, index ->
                root.insertArrangedSubview(
                    view,
                    atIndex = index.convert()
                )
            }
        )
        val composition = RedwoodComposition(
            scope = scope,
            container = children,
            provider = provider
        )
        composition.setContent {
            val value: ProvidedValue<ViewModelStore> = remember(store) {
                LocalViewModelStore provides store
            }

            currentComposer.startProviders(arrayOf(value))
            compose.invoke()
            currentComposer.endProviders()
        }
    }

    override fun tickClock() {
        clock.sendFrame(0L) // Compose does not use frame time.
    }

    override fun dispose() {
        scope.cancel()
        store.clear()
    }
}

interface RedwoodViewControllerDelegate {
    fun tickClock()
    fun dispose()
}
