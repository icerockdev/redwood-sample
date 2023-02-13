package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.Box
import dev.icerock.redwoodapp.ToolabrArgs
import dev.icerock.redwoodapp.ViewModelOwner
import dev.icerock.redwoodapp.navigation.Navigator
import dev.icerock.redwoodapp.navigation.ScreenSettings
import dev.icerock.redwoodapp.screens.demo.navigation.Screens
import org.example.library.MR

@Composable
fun TestCompleteScreen(
    navigator: Navigator,
    tabNavigator: Navigator,
    screenSettings: ScreenSettings<ToolabrArgs>
) {
    LaunchedEffect(screenSettings) {
        screenSettings.setToolbarData(ToolabrArgs.Simple("Результаты теста".desc()))
    }

    Box {
        Column(horizontalAlignment = CrossAxisAlignment.Center) {
            Image(
                120,
                120,
                placeholder = MR.images.checked_big,
                layoutModifier = LayoutModifier.padding(Padding(bottom = 50)),
                url = null
            )
            Text("Тест успешно пройден!", textType = TextType.Header)
            Text("Верных ответов 85%", textType = TextType.Primary)

            Button(
                layoutModifier = LayoutModifier.padding(Padding(top = 50,
                start = 16, end = 16)),
                text = "Продолжить".desc(),
                buttonType = ButtonType.Primary,
                width = Size.Fill,
                onClick = {
                    tabNavigator.popBackStack()
                    tabNavigator.popBackStack()
                    tabNavigator.popBackStack()
                }
            )
        }
    }
}