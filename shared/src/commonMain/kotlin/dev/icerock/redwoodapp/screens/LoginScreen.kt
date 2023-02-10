package dev.icerock.redwoodapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.InputType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.ImageButton
import dev.icerock.redwood.schema.compose.TextInput
import dev.icerock.redwoodapp.navigation.Navigator
import org.example.library.MR
import dev.icerock.redwoodapp.Box
import dev.icerock.redwoodapp.SimpleLoginViewModel
import dev.icerock.redwoodapp.ToolabrArgs
import dev.icerock.redwoodapp.ToolbarAction
import dev.icerock.redwoodapp.ViewModelOwner
import dev.icerock.redwoodapp.getViewModel
import dev.icerock.redwoodapp.navigation.ScreenSettings

@Composable
fun LoginScreen(
    navigator: Navigator,
    screenSettings: ScreenSettings<ToolabrArgs>,
    viewModelOwner: ViewModelOwner
) {

    val viewModel: SimpleLoginViewModel = getViewModel(viewModelOwner) {
        SimpleLoginViewModel()
    }

    val buttontext by viewModel.tetxFlow.collectAsState()

    val isLiked by viewModel.likeResource.collectAsState(MR.images.like)
    val badge by viewModel.badge.collectAsState(null)
    LaunchedEffect(screenSettings, badge, isLiked) {
        screenSettings.setToolbarData(ToolabrArgs.NoToolbar)
       // screenSettings.setToolbarData(
       //     title = ToolabrArgs.Simple(
       //         "Login".desc(),
       //         listOf(ToolbarAction(
       //             MR.images.line,
       //             null
       //         ) {
       //             viewModel.setText("clicked")
       //         }, ToolbarAction(
       //             isLiked,
       //             null
       //         ) {
       //             viewModel.setLike()
       //         }, ToolbarAction(
       //             MR.images.settings,
       //             badge
       //         ) {
       //             viewModel.onNotificationClicl()
       //         })
       //     )
       // )
    }
    Box {
        Column(
            horizontalAlignment = CrossAxisAlignment.Center,
            verticalAlignment = MainAxisAlignment.Center
        ) {
            var login: String by remember { mutableStateOf("") }
            var password: String by remember { mutableStateOf("") }
            Image(
                120,
                120,
                placeholder = MR.images.ava_preview,
                layoutModifier = LayoutModifier.padding(Padding(bottom = 50)),
                url = null
            )
            TextInput(login,
                MR.strings.auth_login.desc(),
                layoutModifier = LayoutModifier.padding(Padding(16)),
                onChange = {
                    login = it
                })
            TextInput(
                password,
                MR.strings.auth_password.desc(),
                layoutModifier = LayoutModifier.padding(Padding(horizontal = 16)),
                onChange = {
                    password = it
                },
                inputType = InputType.Password
            )
            Button(
                buttontext,
                buttonType = ButtonType.Primary,
                enabled = login.isNotEmpty() && password.isNotEmpty(),
                onClick = {
                    navigator.navigate("tabs")
                },
                layoutModifier = LayoutModifier.padding(Padding(start = 16, top = 50, end = 16))
            )
        }
    }
}