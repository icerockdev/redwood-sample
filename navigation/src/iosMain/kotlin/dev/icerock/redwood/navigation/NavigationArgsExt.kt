/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation

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
