package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.Divider
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.ListItem
import dev.icerock.redwood.schema.compose.RowWithWeight
import dev.icerock.redwood.schema.compose.Space
import dev.icerock.redwood.schema.compose.Stack
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.example.library.MR

@Composable
fun TestStepScreen(
    navigator: Navigator,
    step: Int,
    screenSettings: ScreenSettings<ToolabrArgs>,
    viewModelOwner: ViewModelOwner
) {
    val viewModel: TestStepViewModel = getViewModel(viewModelOwner) {
        TestStepViewModel(step)
    }
    val title by viewModel.title.collectAsState()
    LaunchedEffect(screenSettings) {
        viewModel.setStep(step)
    }
    LaunchedEffect(screenSettings, title) {
        screenSettings.setToolbarData(ToolabrArgs.Simple(title.desc()))
    }
    val questions by viewModel.questions.collectAsState()
    val answers by viewModel.answers.collectAsState()
    val qustionTite by viewModel.qustionTitle.collectAsState()
    val currentQuestion by viewModel.qurrentQuestion.collectAsState()
    val isBackButtonEnabled by viewModel.isBackButtonEnabled.collectAsState()
    val qurrentQuestionAnswer by viewModel.qurrentQuestionAnser.collectAsState()
    val isNextButtonEnabled by viewModel.isNextButtonEnabled.collectAsState()
    Stack(
        child1 = {
            Column(
                overflow = Overflow.Scroll,
                horizontalAlignment = CrossAxisAlignment.Start
            ) {
                Row {
                    Text(
                        qustionTite,
                        textType = TextType.Primary, layoutModifier = LayoutModifier.padding(
                            Padding(
                                horizontal = 16,
                                vertical = 16
                            )
                        ),
                        width = Size.Fill
                    )
                }
                currentQuestion?.let { question ->
                    Row {
                        Text(
                            question.qustion,
                            textType = TextType.Bold, layoutModifier = LayoutModifier.padding(
                                Padding(
                                    start = 16,
                                    end = 16,
                                    bottom = 16
                                )
                            ),
                            width = Size.Fill
                        )
                    }

                    question.answers.forEachIndexed { answerIndex, answer ->


                        Divider(layoutModifier = LayoutModifier.padding(Padding(start = 64)))
                        ListItem(
                            title = answer.desc(),
                            subtitle = null,
                            icon = if (qurrentQuestionAnswer == answerIndex) MR.images.radioClicked else MR.images.radio,
                            onClick = { viewModel.setAnswer(answerIndex) }
                        ) {}

                    }
                }

                Divider(layoutModifier = LayoutModifier.padding(Padding(start = 64)))
                Column {
                    Text("   ", layoutModifier = LayoutModifier.padding(Padding(32)))
                }

                Column {
                    Text("   ", layoutModifier = LayoutModifier.padding(Padding(32)))
                }
            }

        },
        child2 = {
            Column(verticalAlignment = MainAxisAlignment.End) {
                RowWithWeight {
                    Button(
                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                top = 16,
                                bottom = 16,
                                end = 8,
                                start = 16
                            )
                        ),
                        text = ("Назад").desc(),
                        buttonType = ButtonType.Primary,
                        enabled = isBackButtonEnabled,
                        width = Size.Fill,
                        onClick = {
                            viewModel.onBackTap()
                        }
                    )
                    Button(
                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                top = 16,
                                bottom = 16,
                                end = 16,
                                start = 8
                            )
                        ),
                        text = ("Далее").desc(),
                        buttonType = ButtonType.Primary,
                        enabled = isNextButtonEnabled,
                        width = Size.Fill,
                        onClick = {
                            if (viewModel.currentQuestionIndex.value != viewModel.questions.value.size - 1) {
                                viewModel.onNextTap()
                                return@Button
                            }
                            if (viewModel.testDetails.value.steps.size == step + 1) {
                                navigator.navigate(Screens.TEST_FINAL)
                            } else {
                                navigator.navigate(Screens.testStep(step + 1))
                            }
                        }
                    )
                }
            }
        }
    )
}

class TestStepViewModel(step: Int) : ViewModel() {
    val _step = MutableStateFlow(step)
    val testDetails = MutableStateFlow(Mock.FIRT_TEST)
    val testStep = testDetails.combine(_step) { test, step -> test.steps[step] }
    val title = testStep.map { it.title }.stateIn(viewModelScope, SharingStarted.Lazily, "")
    val questions =
        testStep.map { it.questions }
            .stateIn(viewModelScope, SharingStarted.Lazily, listOf())
    val currentQuestionIndex = MutableStateFlow(0)
    val qurrentQuestion = questions.combine(currentQuestionIndex) { list, index ->
        list.getOrNull(index)
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)


    val isBackButtonEnabled = currentQuestionIndex.map { it != 0 }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    val answers = MutableStateFlow<Map<Int, Int>>(mapOf())
    val qurrentQuestionAnser = answers.combine(currentQuestionIndex) { answer, index ->
        answer.get(index)
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)
    val isNextButtonEnabled = qurrentQuestionAnser.map { it != null }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)
    val qustionTitle = currentQuestionIndex.combine(questions) { index, qusetions ->
        "Вопрос ${index+1} из ${qusetions.size}"
    }.stateIn(viewModelScope, SharingStarted.Lazily, "")

    fun setStep(step: Int) {
        _step.value = step
        answers.value = mapOf()
    }

    fun setAnswer(answer: Int) {
        val map = mutableMapOf<Int, Int>()
        map.putAll(answers.value)
        map.put(currentQuestionIndex.value, answer)
        answers.value = map
    }

    fun onNextTap() {
        currentQuestionIndex.value = currentQuestionIndex.value + 1
    }

    fun onBackTap() {
        currentQuestionIndex.value = currentQuestionIndex.value - 1
    }
}

data class TestDetails(
    val id: Int,
    val steps: List<TestStep>
)

data class TestStep(
    val id: Int,
    val title: String,
    val questions: List<TestQuestion>
)

data class TestQuestion(
    val qustion: String,
    val answers: List<String>
)


