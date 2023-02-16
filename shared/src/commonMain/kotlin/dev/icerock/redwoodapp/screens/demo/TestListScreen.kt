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
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.navigation.navbar.NavBarController
import dev.icerock.redwood.navigation.navbar.rememberNavBarController
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.navigation.viewmodel.getViewModel
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Tabs
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.ToolbarArgs
import dev.icerock.redwoodapp.screens.demo.navigation.Screens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.example.library.MR

@Composable
fun TestListScreen(
    navigator: Navigator
) {
    val viewModel: TestListViewModel = getViewModel {
        TestListViewModel()
    }
    val navBarController: NavBarController = rememberNavBarController()

    LaunchedEffect(navBarController) {
        navBarController.navBarData = ToolbarArgs.NoToolbar
    }

    Column(width = Constraint.Fill, overflow = Overflow.Scroll) {
        val tests: List<Test> by viewModel.testList.collectAsState()

        val selectedTab by viewModel.selectedTab.collectAsState()
        Tabs(
            listOf("Нужно пройти".desc(), "Пройденные".desc()),
            listOf({ viewModel.onTabClick(0) }, { viewModel.onTabClick(1) }),
            selectedTab
        )

        tests.forEach {
            Card(
                layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
                onClick = { navigator.navigate(Screens.testStep(0)) },
                child = {
                    Column(
                        width = Constraint.Fill,
                        horizontalAlignment = CrossAxisAlignment.Start
                    ) {
                        Text(
                            layoutModifier = LayoutModifier.padding(
                                Padding(
                                    start = 16,
                                    end = 16,
                                    top = 12
                                )
                            ), text = it.testName, textType = TextType.Primary
                        )
                        Text(
                            layoutModifier = LayoutModifier.padding(
                                Padding(
                                    start = 16,
                                    end = 16,
                                    top = 6
                                )
                            ), text = it.subtite, textType = TextType.Primary
                        )
                        Row(
                            layoutModifier = LayoutModifier.padding(
                                Padding(
                                    start = 16,
                                    end = 16,
                                    top = 10,
                                    bottom = 12
                                )
                            ),
                            verticalAlignment = CrossAxisAlignment.Center
                        ) {
                            Image(
                                width = Size.Const(24),
                                height = Size.Const(24),
                                placeholder = MR.images.test_check,
                                url = null)
                            Text(
                                layoutModifier = LayoutModifier.padding(
                                    Padding(
                                        start = 8,
                                    )
                                ), text = "Пройти до 17 Августа 2023", textType = TextType.Accent
                            )
                        }
                    }
                })
        }
    }
}

class TestListViewModel() : ViewModel() {
    val selectedTab = MutableStateFlow(0)
    val allTestList = MutableStateFlow(Mock.TEST_LIST)
    val testList = allTestList.combine(selectedTab) { list, index ->
        if (index == 0) listOf(list.first())
        else {
            list.subList(1, list.size)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    fun onTabClick(index: Int) {
        selectedTab.value = index
    }
}

data class Test(
    val id: Int,
    val isFinished: Boolean,
    val testName: String,
    val subtite: String,
)

