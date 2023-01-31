package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc

expect inline fun <reified VM : ViewModel> getViewModel(
    viewModelOwner: ViewModelOwner,
    crossinline factory: () -> VM
): VM

expect class ViewModelOwner

class SimpleLoginViewModel() : ViewModel() {
    val LoginButtonTitle: StringDesc = "VIEWMODEL TEXT".desc()
}

class SimpleListViewModel() : ViewModel() {
    val listData = NEWS_LIST
}