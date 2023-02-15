package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Onboarding
import dev.icerock.redwood.schema.compose.Text
import org.example.library.MR

@Composable
fun OnboardingScreen(routeToLogin: () -> Unit) {
    Onboarding(onFinishClick = routeToLogin,
        childs = {
            for (i in 0..3) {
                Column(
                    height = Constraint.Fill,
                    horizontalAlignment = CrossAxisAlignment.Start
                ) {
                    Image(
                        width = Size.Fill,
                        aspectRatio = 1f,
                        height = null,
                        url = null,
                        placeholder = MR.images.onboarding
                    )
                    Text(
                        "Повышение производительности труда",
                        textType = TextType.Header,
                        layoutModifier = LayoutModifier.padding(Padding(horizontal = 16))
                    )
                    Text(
                        "Автоматизируем процесс ПМО, улучшая его качество и объективность " + i.toString(),
                        textType = TextType.Primary,
                        layoutModifier = LayoutModifier.padding(Padding(16))
                    )
                }
            }
        }
    )
}