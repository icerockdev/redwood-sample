/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.currentComposer
import app.cash.redwood.widget.Widget
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.redwood.navigation.navbar.LocalNavBarController
import dev.icerock.redwood.navigation.navbar.NavBarController
import dev.icerock.redwood.navigation.navigator.HistoryNavigator
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.navigation.scope.NavigationFactoryScope
import dev.icerock.redwood.navigation.scope.NavigationFlatScope
import dev.icerock.redwood.navigation.scope.NavigationScope
import dev.icerock.redwood.navigation.scope.NavigationTabsScope
import platform.UIKit.UINavigationController
import platform.UIKit.UIStackView
import platform.UIKit.UITabBarController
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import platform.UIKit.tabBarItem

typealias ViewControllerFactory<Nav> = (Widget.Provider<UIView>, Nav, Map<String, String>) -> UIViewController

actual fun navigation(
    navigationFactory: NavigationFactory,
    startDestination: String,
    block: NavigationScope.() -> Unit
): NavigationRoot {
    val routes: MutableMap<String, ViewControllerFactory<Navigator>> = mutableMapOf()

    val scope: NavigationFactoryScope = object : NavigationFactoryScope {
        override fun navigationFlat(
            startDestination: String,
            block: NavigationFlatScope.() -> Unit
        ): NavigationHost {
            return FlatNavigationHost(navigationFactory, startDestination, block)
        }

        override fun navigationTabs(
            startDestination: String,
            block: NavigationTabsScope.() -> Unit
        ): NavigationHost {
            return TabsNavigationHost(navigationFactory, startDestination, block)
        }
    }

    object : NavigationScope {
        override fun registerNavigation(
            uri: String,
            childNavigation: NavigationFactoryScope.(Navigator, Map<String, String>) -> NavigationHost
        ) {
            routes[uri] = { provider, navigator, args ->
                scope.childNavigation(navigator, args).createViewController(provider)
            }
        }

        override fun registerScreen(
            uri: String,
            screen: @Composable (Navigator, Map<String, String>) -> Unit
        ) {
            routes[uri] = { provider, navigator, args ->
                navigationFactory.createComposeScreen { stackView: UIStackView ->
                    RedwoodViewControllerDelegateImpl(
                        root = stackView,
                        compose = @Composable { screen(navigator, args) },
                        provider = provider
                    )
                }
            }
        }
    }.block()

    val root: NavigationRoot = navigationFactory.createRootNavigation()

    val navigator = object : Navigator {
        override fun navigate(uri: String) {
            val vc: UIViewController = routes.createDestinationViewController(
                provider = navigationFactory.provider,
                navigator = this,
                destination = uri
            )
            root.setViewController(vc)
        }
    }

    val initialVC: UIViewController = routes.createDestinationViewController(
        provider = navigationFactory.provider,
        navigator = navigator,
        destination = startDestination
    )
    root.setViewController(initialVC)

    return root
}

class FlatNavigationHost(
    private val navigationFactory: NavigationFactory,
    private val startDestination: String,
    block: NavigationFlatScope.() -> Unit
) : NavigationHost {
    private val routes: MutableMap<String, ViewControllerFactory<HistoryNavigator>> =
        mutableMapOf()

    init {
        @OptIn(InternalComposeApi::class)
        object : NavigationFlatScope {
            override fun registerScreen(
                uri: String,
                screen: @Composable (HistoryNavigator, Map<String, String>) -> Unit
            ) {
                routes[uri] = { provider, navigator, args ->
                    lateinit var vc: UIViewController
                    vc = navigationFactory.createComposeScreen { stackView ->
                        RedwoodViewControllerDelegateImpl(
                            root = stackView,
                            compose = @Composable {
                                val navBarProvider: ProvidedValue<NavBarController> =
                                    LocalNavBarController provides navigationFactory.createNavBarController(
                                        vc
                                    )

                                currentComposer.startProviders(arrayOf(navBarProvider))
                                screen(navigator, args)
                                currentComposer.endProviders()
                            },
                            provider = provider
                        )
                    }
                    vc
                }
            }

            override fun registerNavigation(
                uri: String,
                childNavigation: (HistoryNavigator, Map<String, String>) -> NavigationHost
            ) {
                routes[uri] = { provider, navigator, args ->
                    childNavigation(navigator, args).createViewController(provider)
                }
            }
        }.block()
    }

    override fun createViewController(provider: Widget.Provider<UIView>): UIViewController {
        lateinit var navController: UINavigationController
        val navigator: HistoryNavigator = object : HistoryNavigator {
            override fun popBackStack() {
                navController.popViewControllerAnimated(animated = true)
            }

            override fun popToRoot() {
                navController.popToRootViewControllerAnimated(animated = true)
            }

            override fun navigate(uri: String) {
                val vc: UIViewController = routes.createDestinationViewController(
                    provider = provider,
                    navigator = this,
                    destination = uri
                )
                navController.pushViewController(vc, animated = true)
            }
        }

        val startVC: UIViewController = routes.createDestinationViewController(
            provider = provider,
            navigator = navigator,
            destination = startDestination
        )
        navController = UINavigationController(
            rootViewController = startVC
        )
        return navController
    }
}

class TabsNavigationHost(
    private val navigationFactory: NavigationFactory,
    private val startDestination: String,
    block: NavigationTabsScope.() -> Unit
) : NavigationHost {
    private val routes: MutableMap<String, TabRouteData> =
        mutableMapOf()

    init {
        object : NavigationTabsScope {
            override fun registerScreen(
                uri: String,
                title: StringDesc?,
                icon: ImageResource?,
                screen: @Composable (Navigator) -> Unit
            ) {
                routes[uri] = TabRouteData(
                    title = title,
                    icon = icon,
                    createViewController = { provider, navigator ->
                        navigationFactory.createComposeScreen { stackView ->
                            RedwoodViewControllerDelegateImpl(
                                root = stackView,
                                compose = @Composable { screen(navigator) },
                                provider = provider
                            )
                        }
                    }
                )
            }

            override fun registerNavigation(
                uri: String,
                title: StringDesc?,
                icon: ImageResource?,
                childNavigation: (Navigator) -> NavigationHost
            ) {
                routes[uri] = TabRouteData(
                    title = title,
                    icon = icon,
                    createViewController = { provider, navigator ->
                        childNavigation(navigator).createViewController(provider)
                    }
                )
            }
        }.block()
    }

    override fun createViewController(provider: Widget.Provider<UIView>): UIViewController {
        val tabBarController = UITabBarController()
        val navigator: Navigator = object : Navigator {
            override fun navigate(uri: String) {
                val index: Int = routes.keys.indexOf(uri)
                tabBarController.setSelectedIndex(selectedIndex = index.toULong())
            }
        }

        val controllers: List<UIViewController> = routes.map { (_, value) ->
            val vc: UIViewController = value.createViewController(provider, navigator)
            vc.tabBarItem.title = value.title?.localized()
            vc.tabBarItem.image = value.icon?.toUIImage()
            vc
        }
        val startIndex: Int = routes.keys.indexOf(startDestination)
        tabBarController.setViewControllers(controllers, animated = false)
        tabBarController.setSelectedIndex(startIndex.toULong())
        return tabBarController
    }
}

data class TabRouteData(
    val title: StringDesc?,
    val icon: ImageResource?,
    val createViewController: (Widget.Provider<UIView>, Navigator) -> UIViewController
) {
    init {
        assert(title != null || icon != null) { "tab should have at least title or icon" }
    }
}
