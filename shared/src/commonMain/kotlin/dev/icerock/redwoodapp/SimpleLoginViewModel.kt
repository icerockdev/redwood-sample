package dev.icerock.redwoodapp

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.example.library.MR

class SimpleLoginViewModel() : ViewModel() {
    val loginButtonTitle: StringDesc = "Login".desc()

    private val _textFlow = MutableStateFlow<StringDesc>("Login".desc())
    val tetxFlow: StateFlow<StringDesc> = _textFlow
    private val _notification = MutableStateFlow<Int>(0)
    val badge: StateFlow<StringDesc?> = _notification.map { it.toString().desc() }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val likeFlow = MutableStateFlow(false)
    val likeResource: StateFlow<ImageResource> = likeFlow.map {
        if (it) MR.images.like_cliked else MR.images.like
    }.stateIn(viewModelScope, SharingStarted.Lazily, MR.images.like)

    fun setText(text: String) {
        _textFlow.value = text.desc()
    }

    fun onNotificationClick() {
        _notification.value = _notification.value + 1
    }

    fun setLike() {
        likeFlow.value = likeFlow.value.not()
    }
}
