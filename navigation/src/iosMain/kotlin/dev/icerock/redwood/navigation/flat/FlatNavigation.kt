/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.flat

import app.cash.redwood.widget.Widget
import dev.icerock.redwood.navigation.NavigationHost
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.navigation.getParams
import dev.icerock.redwood.navigation.getStableParts
import dev.icerock.redwood.navigation.isPlaceholderOf
import platform.UIKit.UINavigationController
import platform.UIKit.UIView
import platform.UIKit.UIViewController


data class FlatNavigation<T>(
    val startDestination: String,
    val routes: MutableMap<String, FlatRouteData>,
//    val viewModelOwners: MutableMap<String, ViewModelOwner>
) : NavigationHost {

    override fun createViewController(provider: Widget.Provider<UIView>): UIViewController {
        lateinit var navController: UINavigationController

        val navigator: Navigator = object : Navigator {
            override fun navigate(uri: String) {
                val startUri = uri.substringBefore('?')
                val key = routes.keys.find { it.isPlaceholderOf(startUri) } ?: return
                val params = uri.substringAfter('?')
                val paramsMap = mutableMapOf<String, String>()
                params.split('&').forEach {
                    paramsMap[it.substringBefore('=')] = it.substringAfter('=')
                }
                val newViewController: UIViewController =
                    routes[key]!!(
                        provider,
                        this,
                        paramsMap
                            .apply {
                                putAll(startUri.getParams(key, key.getStableParts()))
                            },
//                        viewModelOwners[startDestination]!!
                    )
                navController.pushViewController(newViewController, animated = false)
            }

            override fun popBackStack() {
                navController.popViewControllerAnimated(false)
            }
        }
        val rootViewController: UIViewController =
            routes[startDestination]!!(
                provider,
                navigator,
                emptyMap(),
//                viewModelOwners[startDestination]!!
            )
        navController = UINavigationController(rootViewController = rootViewController)
        return navController
    }
}

actual interface FlatNavigationFactory<T> {

    fun render(viewController: UIViewController, data: T)
}
