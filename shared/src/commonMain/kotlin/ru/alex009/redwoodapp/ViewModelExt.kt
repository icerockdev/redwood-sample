package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import org.example.library.MR
import ru.alex009.redwoodapp.navigation.ScreenSettings

@Composable
expect inline fun <reified VM: ViewModel> getViewModel(
    screenSettings: ScreenSettings,
    crossinline factory: ()->VM
): VM


class SimpleLoginViewModel(): ViewModel(){
    val LoginButtonTitle: StringDesc = MR.strings.auth_login.desc()
}