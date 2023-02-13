/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.flat

//
//typealias FlatRouteData = (Widget.Provider<UIView>, Navigator, Map<String, String>) -> UIViewController
//
//actual fun <T : Any> navigationFlat(
//    startDestination: String,
//    factory: FlatNavigationFactory<T>,
//    block: NavigationFlatScope<T>.() -> Unit
//): NavigationHost {
//    val routes: MutableMap<String, FlatRouteData> = mutableMapOf()
////    val viewModelOwners: MutableMap<String, ViewModelOwner> = mutableMapOf()
//
//    val dsl = object : NavigationFlatScope<T> {
//        override fun registerScreen(
//            uri: String,
//            isToolbarVisible: Boolean,
//            screen: @Composable (
//                Navigator,
//                Map<String, String>,
//                ScreenSettings<T>,
////                ViewModelOwner
//            ) -> Unit
//        ) {
//            val startUri = uri.substringBefore('?')
//            routes[startUri] = { provider, navigator, args ->
//                val screenSettings = ScreenSettingsImpl(factory)
//                ComposeViewController(
//                    provider,
////                    viewModelOwner
//                ) @Composable {
//                    screen(
//                        navigator,
//                        args,
//                        screenSettings,
////                        viewModelOwner
//                    )
//                }.also {
//                    screenSettings.init(it)
//                }
//            }
////            viewModelOwners[startUri] = ViewModelOwner(mutableMapOf())
//        }
//
//        override fun registerNavigation(
//            uri: String,
//            isToolbarVisible: Boolean,
//            childNavigation: (Navigator, Map<String, String>, ScreenSettings<T>) -> NavigationHost
//        ) {
//            val startUri = uri.substringBefore('?')
//            routes[startUri] = { provider, navigator, args ->
//                val screenSettings = ScreenSettingsImpl(factory)
//                childNavigation(
//                    navigator,
//                    args,
//                    screenSettings,
////                    viewModelOwner
//                ).createViewController(provider).also {
//                    screenSettings.init(it)
//                }
//            }
////            viewModelOwners[startUri] = ViewModelOwner(mutableMapOf())
//        }
//    }
//    dsl.block()
//    return FlatNavigation<T>(
//        startDestination = startDestination,
//        routes = routes,
////        viewModelOwners = viewModelOwners
//    )
//}
