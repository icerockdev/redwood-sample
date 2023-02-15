package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.navigation.navbar.NavBarController
import dev.icerock.redwood.navigation.navbar.rememberNavBarController
import dev.icerock.redwood.navigation.navigator.HistoryNavigator
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.Box
import dev.icerock.redwoodapp.ToolbarArgs
import org.example.library.MR

@Composable
fun TestCompleteScreen(
    navigator: HistoryNavigator
) {
    val navBarController: NavBarController = rememberNavBarController()

    LaunchedEffect(navBarController) {
        navBarController.navBarData = ToolbarArgs.Simple("Результаты теста".desc())
    }

    Box {
        Column(horizontalAlignment = CrossAxisAlignment.Center) {
            Image(
                width = Size.Const(120),
                height = Size.Const(120),
                placeholder = MR.images.checked_big,
                layoutModifier = LayoutModifier.padding(Padding(bottom = 50)),
                url = null
            )
            Text("Тест успешно пройден!", textType = TextType.Header)
            Text("Верных ответов 85%", textType = TextType.Primary)

            Button(
                layoutModifier = LayoutModifier.padding(
                    Padding(
                        top = 50,
                        start = 16, end = 16
                    )
                ),
                text = "Продолжить".desc(),
                buttonType = ButtonType.Primary,
                width = Size.Fill,
                onClick = {
                    navigator.popToRoot()
                }
            )
        }
    }
}
