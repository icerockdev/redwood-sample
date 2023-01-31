package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import ru.alex009.redwoodapp.navigation.ScreenSettings

@Composable
actual inline fun <reified VM : ViewModel> getViewModel(
    viewModelOwner: ViewModelOwner,
    crossinline factory: () -> VM
): VM {
    return viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return factory() as T
            }
        })
}

actual class ViewModelOwner