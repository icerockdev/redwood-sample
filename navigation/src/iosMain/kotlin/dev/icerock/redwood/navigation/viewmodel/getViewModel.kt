/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.redwood.navigation.viewmodel

import androidx.compose.runtime.Composable
import dev.icerock.moko.mvvm.viewmodel.ViewModel

@Composable
actual inline fun <reified VM : ViewModel> getViewModel(
    crossinline factory: () -> VM
): VM {
    TODO()
//    return viewModelOwner.getViewModel(factory)
}
//
//actual class ViewModelOwner(
//    val mutableMap: MutableMap<String, ViewModel>
//) {
//    inline fun <reified VM : ViewModel> getViewModel(factory: () -> VM): VM {
//        val key = VM::class.simpleName.orEmpty()
//        if (mutableMap.containsKey(key).not()) {
//            mutableMap[key] = factory.invoke()
//        }
//        return mutableMap[key]!! as VM
//    }
//}
