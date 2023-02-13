/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation

import androidx.compose.runtime.Composable
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.navigation.scope.NavigationFactoryScope
import dev.icerock.redwood.navigation.scope.NavigationFlatScope
import dev.icerock.redwood.navigation.scope.NavigationScope
import dev.icerock.redwood.navigation.scope.NavigationTabsScope
import platform.UIKit.UIStackView
import platform.UIKit.UIViewController

actual fun navigation(
    navigationFactory: NavigationFactory,
    startDestination: String,
    block: NavigationScope.() -> Unit
): NavigationRoot {
    val routes: MutableMap<String, (Navigator, Map<String, String>) -> UIViewController> =
        mutableMapOf()

    val scope: NavigationFactoryScope = object : NavigationFactoryScope {
        override fun navigationFlat(
            startDestination: String,
            block: NavigationFlatScope.() -> Unit
        ): NavigationHost {
            TODO("Not yet implemented")
        }

        override fun navigationTabs(
            startDestination: String,
            block: NavigationTabsScope.() -> Unit
        ): NavigationHost {
            TODO("Not yet implemented")
        }
    }

    object : NavigationScope {
        override fun registerNavigation(
            uri: String,
            childNavigation: NavigationFactoryScope.(Navigator, Map<String, String>) -> NavigationHost
        ) {
            routes[uri] = { navigator, args ->
                scope.childNavigation(navigator, args)
                    .createViewController(navigationFactory.provider)
            }
        }

        override fun registerScreen(
            uri: String,
            screen: @Composable (Navigator, Map<String, String>) -> Unit
        ) {
            routes[uri] = { navigator, args ->
                navigationFactory.createComposeScreen { stackView: UIStackView ->
                    RedwoodViewControllerDelegateImpl(
                        root = stackView,
                        compose = @Composable { screen(navigator, args) },
                        provider = navigationFactory.provider
                    )
                }
            }
        }
    }.block()

    val root: NavigationRoot = navigationFactory.createRootNavigation()

    val navigator = object : Navigator {
        override fun navigate(uri: String) {
            val vc: UIViewController = routes.createDestinationViewController(this, uri)
            root.setViewController(vc)
        }
    }

    root.setViewController(routes.createDestinationViewController(navigator, startDestination))

    return root
}
