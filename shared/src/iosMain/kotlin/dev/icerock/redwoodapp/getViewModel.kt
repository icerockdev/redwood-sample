package dev.icerock.redwoodapp

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.redwoodapp.ViewModelOwner

actual inline fun <reified VM : ViewModel> getViewModel(
    viewModelOwner: ViewModelOwner,
    crossinline factory: () -> VM
): VM {
    return viewModelOwner.getViewModel(factory)
}

actual class ViewModelOwner(
    val mutableMap: MutableMap<String, ViewModel>
) {
    inline fun <reified VM : ViewModel> getViewModel(factory: () -> VM): VM {
        val key = VM::class.simpleName.orEmpty()
        if (mutableMap.containsKey(key).not()) {
            mutableMap[key] = factory.invoke()
        }
        return mutableMap[key]!! as VM
    }
}