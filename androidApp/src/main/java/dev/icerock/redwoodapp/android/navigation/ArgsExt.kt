/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwoodapp.android.navigation


fun String.getArgs(): List<String> {
    val result = mutableListOf<String>()

    val tmp = this.substringBefore('?').split('/')
    if (tmp.isEmpty() || tmp.size == 1) return listOf()
    tmp.forEach {
        if (it.contains('}')) {
            result.add(
                it.removePrefix("{")
                    .removeSuffix("}")
            )
        }
    }

    return result
}

fun String.getParams(): List<String> {
    val result = mutableListOf<String>()

    val tmp = this.substringAfter('?').split('&')
    if (tmp.isEmpty() || tmp.size == 1) return listOf()
    tmp.forEach {
        if (it.contains('}')) {
            result.add(
                it.removePrefix("{")
                    .removeSuffix("}")
            )
        }
    }

    return result
}
