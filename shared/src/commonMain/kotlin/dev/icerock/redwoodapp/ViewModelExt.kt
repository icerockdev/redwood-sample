package dev.icerock.redwoodapp

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import org.example.library.MR
expect inline fun <reified VM : ViewModel> getViewModel(
    viewModelOwner: ViewModelOwner,
    crossinline factory: () -> VM
): VM

expect class ViewModelOwner

class SimpleLoginViewModel() : ViewModel() {
    val LoginButtonTitle: StringDesc = "Login".desc()

    private val _textFlow = MutableStateFlow<StringDesc>("Login".desc())
    val tetxFlow: StateFlow<StringDesc> = _textFlow
    private val _notification = MutableStateFlow<Int>(0)
    val badge: Flow<StringDesc> = _notification.map { it.toString().desc() }

    val likeFlow = MutableStateFlow(false)
    val likeResource = likeFlow.map {
        if(it) MR.images.like_cliked else MR.images.like
    }
    fun setText(text: String) {
        _textFlow.value = text.desc()
    }

    fun onNotificationClicl() {
        _notification.value = _notification.value + 1
    }

    fun setLike() {
        likeFlow.value = likeFlow.value.not()
    }
}

class SimpleListViewModel() : ViewModel() {
    val listData = NEWS_LIST
}