/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.tabs

//
//data class TabRouteData(
//    val title: StringDesc?,
//    val icon: ImageResource?,
//    val createViewController: (Widget.Provider<UIView>, Navigator) -> UIViewController
//)
//
//actual fun navigationTabs(
//    startDestination: String,
//    block: NavigationTabsScope.() -> Unit
//): NavigationHost {
//    val routes: MutableMap<String, TabRouteData> = mutableMapOf()
////    val viewModelOwners: MutableMap<String, ViewModelOwner> = mutableMapOf()
//
//    val dsl = object : NavigationTabsScope {
//        override fun registerScreen(
//            uri: String,
//            title: StringDesc?,
//            icon: ImageResource?,
//            screen: @Composable (Navigator) -> Unit
//        ) {
////            viewModelOwners[uri] = ViewModelOwner(mutableMapOf())
//            routes[uri] = TabRouteData(
//                title = title,
//                icon = icon,
//                createViewController = { provider, navigator ->
//                    ComposeViewController(
//                        provider,
////                        viewModelOwners[uri]!!
//                    ) @Composable {
//                        screen(navigator)
//                    }
//                }
//            )
//        }
//
//        override fun registerNavigation(
//            uri: String,
//            title: StringDesc?,
//            icon: ImageResource?,
//            childNavigation: (Navigator) -> NavigationHost
//        ) {
//            routes[uri] = TabRouteData(
//                title = title,
//                icon = icon,
//                createViewController = { provider, navigator ->
//                    childNavigation(navigator).createViewController(provider)
//                }
//            )
//        }
//    }
//    dsl.block()
//    return TabNavigation(startDestination, routes)
//}
