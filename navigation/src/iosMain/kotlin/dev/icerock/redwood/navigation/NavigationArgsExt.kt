/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation

import app.cash.redwood.widget.Widget
import dev.icerock.redwood.navigation.navigator.Navigator
import platform.UIKit.UIView
import platform.UIKit.UIViewController

internal fun <Nav : Navigator> Map<String, ViewControllerFactory<Nav>>.createDestinationViewController(
    provider: Widget.Provider<UIView>,
    navigator: Nav,
    destination: String
): UIViewController {
    val (data, args) = this.firstNotNullOf { (uri, data) ->
        val args: Map<String, String> = destination.readUriArgs(uri) ?: return@firstNotNullOf null
        data to args
    }
    return data(provider, navigator, args)
}

internal fun String.readUriArgs(route: String): Map<String, String>? {
    val startUri: String = this.substringBefore('?')
    if (!route.isPlaceholderOf(startUri)) return null

    val params: String = this.substringAfter('?')
    val paramsMap: MutableMap<String, String> = mutableMapOf()
    params.split('&').forEach {
        paramsMap[it.substringBefore('=')] = it.substringAfter('=')
    }
    return paramsMap + startUri.getParams(route, route.getStableParts())
}

internal fun String.getParams(placeholder: String) =
    this.getParams(placeholder, placeholder.getStableParts())

internal fun String.getStableParts(): List<String> {
    if (contains('{').not()) return listOf(this)
    return split('{').map { it.split('}').last() }
}

internal fun String.isPlaceholderOf(value: String): Boolean {
    return value.checkPlaceholderPart(getStableParts())
}

internal fun String.checkPlaceholderPart(stablePart: List<String>): Boolean {
    if (isEmpty() && stablePart.isEmpty()) return true
    if (stablePart.size == 1 && stablePart.get(0).isEmpty()) return true
    if (stablePart.size == 1 && this.equals(stablePart.get(0))) return true
    return contains(stablePart.get(0)) && substringAfter(stablePart.get(0)).checkPlaceholderPart(
        stablePart.subList(
            1,
            stablePart.size
        )
    )
}

internal fun String.getParams(placeholder: String, stablePart: List<String>): Map<String, String> {
    if (isEmpty() && stablePart.isEmpty()) return mapOf()
    if (stablePart.size == 1 && this.equals(stablePart.get(0))) return mapOf()
    val mapParams = mutableMapOf<String, String>()
    val currentStablePart = stablePart.get(0)
    if (currentStablePart.isEmpty()) {
        mapParams[placeholder.removePrefix("{").removeSuffix("}")] = this
        return mapParams
    }
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
