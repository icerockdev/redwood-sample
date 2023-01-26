package ru.alex009.redwoodapp.navigation

import app.cash.redwood.widget.Widget
import platform.UIKit.UINavigationController
import platform.UIKit.UIView
import platform.UIKit.UIViewController

data class FlatNavigation(
    val startDestination: String,
    val routes: MutableMap<String, FlatRouteData>
) : NavigationHost {

    override fun createViewController(provider: Widget.Provider<UIView>): UIViewController {
        lateinit var navController: UINavigationController
        val navigator: Navigator = object : Navigator {
            override fun navigate(uri: String) {
                val startUri = uri.substringBefore('?')
                val params = uri.substringAfter('?')
                val paramsMap = mutableMapOf<String, String>()
                params.split('&').forEach {
                    paramsMap[it.substringBefore('=')] = it.substringAfter('=')
                }
                val key = routes.keys.find { it.isPlaceHolderOf(startUri) } ?: return
                val newViewController: UIViewController =
                    routes[key]!!(provider, this, paramsMap.apply {
                        startUri.getParams(key, key.getStableParts())
                    })
                navController.pushViewController(newViewController, animated = true)
            }

            override fun popBackStack() {
                navController.popViewControllerAnimated(true)
            }
        }
        val rootViewController: UIViewController =
            routes[startDestination]!!(provider, navigator, emptyMap())
        navController = UINavigationController(rootViewController)
        return navController
    }
}

fun String.getStableParts(): List<String> {
    if(contains('{').not()) return listOf(this)
    return split('{').map { it.split('}').last() }
}

fun String.isPlaceHolderOf(value: String): Boolean {
    return value.checkPlaceholderPart(getStableParts())
}

fun String.checkPlaceholderPart(stablePart: List<String>): Boolean {
    if (isEmpty() && stablePart.isEmpty()) return true
    if (stablePart.size == 1 && this.equals(stablePart.get(0))) return true
    return substringAfter(stablePart.get(0)).checkPlaceholderPart(
        stablePart.subList(
            1,
            stablePart.size
        )
    )
}

fun String.getParams(placeholder: String, stablePart: List<String>): Map<String, String> {
    if (isEmpty() && stablePart.isEmpty()) return mapOf()
    if (stablePart.size == 1 && this.equals(stablePart.get(0))) return mapOf()
    val mapParams = mutableMapOf<String, String>()
    val currentStablePart = stablePart.get(0)
    mapParams[placeholder.substringBefore(currentStablePart)
        .removePrefix("{")
        .removeSuffix("}")] = substringBefore(currentStablePart)
    val otherParams = substringAfter(currentStablePart).getParams(
        placeholder.substringAfter(currentStablePart),
        stablePart.subList(1, stablePart.size)
    )
    mapParams.putAll(otherParams)
    return mapParams.filterKeys { it.isNotEmpty() }
}
