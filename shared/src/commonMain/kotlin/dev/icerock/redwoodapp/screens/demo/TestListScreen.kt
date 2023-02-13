package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Space
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.SimpleLoginViewModel
import dev.icerock.redwoodapp.ToolabrArgs
import dev.icerock.redwoodapp.ViewModelOwner
import dev.icerock.redwoodapp.getViewModel
import dev.icerock.redwoodapp.navigation.Navigator
import dev.icerock.redwoodapp.navigation.ScreenSettings
import kotlinx.coroutines.flow.MutableStateFlow
import dev.icerock.redwoodapp.screens.demo.Mock
import dev.icerock.redwoodapp.screens.demo.navigation.Screens
import org.example.library.MR

@Composable
fun TestListScreen(
    navigator: Navigator,
    screenSettings: ScreenSettings<ToolabrArgs>,
    viewModelOwner: ViewModelOwner
) {
    val viewModel: TestListViewModel = getViewModel(viewModelOwner) {
        TestListViewModel()
    }

    LaunchedEffect(screenSettings) {
        screenSettings.setToolbarData(ToolabrArgs.Simple("Test list".desc()))
    }

    Column(width = Constraint.Fill, overflow = Overflow.Scroll) {
        val tests by viewModel.testList.collectAsState()

        tests.forEach {
            Card(
                layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
                onClick = { navigator.navigate(Screens.testStep(0)) },
                child = {
                    Row(width = Constraint.Fill, verticalAlignment = CrossAxisAlignment.Center) {
                        Image(
                            32,
                            32,
                            null,
                            if (it.isFinished) MR.images.check else MR.images.error,
                            layoutModifier = LayoutModifier.padding(Padding(8)),
                        )
                        Text(text = it.testName, textType = TextType.Primary)
                    }
                })
        }
    }
}

class TestListViewModel() : ViewModel() {
    val testList = MutableStateFlow(Mock.TEST_LIST)
}

data class Test(
    val id: Int,
    val isFinished: Boolean,
    val testName: String,
)

