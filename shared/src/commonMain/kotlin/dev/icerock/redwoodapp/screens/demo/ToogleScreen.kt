package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.navigation.viewmodel.getViewModel
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Text
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.example.library.MR

@Composable
fun ToogleScreen() {
    val viewModel: ToggleViewModel = getViewModel { ToggleViewModel() }

    Column(horizontalAlignment = CrossAxisAlignment.Start, height = Constraint.Fill) {
        val isStarted by viewModel.isToggleStart.collectAsState()

        Card(layoutModifier = LayoutModifier.padding(Padding(16)),
            child = {
                Column(horizontalAlignment = CrossAxisAlignment.Center) {
                    Text(
                        "Сегодня, 19 Февраля", textType = TextType.H2, width = Size.Fill,
                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                start = 16,
                                end = 16,
                                top = 16
                            )
                        ).horizontalAlignment(CrossAxisAlignment.Start)
                    )
                    Image(
                        Size.Const(196),
                        Size.Const(196),
                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                48
                            )
                        ),
                        placeholder = MR.images.time_placeholder,
                        url = null
                    )

                    Button(
                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                start = 16, end = 16
                            )
                        ),
                        text = if (isStarted.not()) "Начать день".desc()
                        else "Закончить рабочий день".desc(),
                        buttonType = if (isStarted) ButtonType.Secondary else ButtonType.Primary,
                        icon = if(isStarted.not()) MR.images.play else MR.images.pause_fill,
                        width = Size.Fill,
                        onClick = {
                            viewModel.startOrStopTimer()
                        }
                    )

                    Button(
                        layoutModifier = LayoutModifier.padding(
                            Padding(
                                start = 16, end = 16,
                                bottom = 16, top = 8
                            )
                        ),
                        text = "История работы".desc(),
                        buttonType = ButtonType.Text,
                        width = Size.Fill,
                        onClick = {
                            // do nothing
                        },
                        icon = MR.images.clock
                    )
                }
            }
        )
    }

}

class ToggleViewModel() : ViewModel() {

    val isToggleStart = MutableStateFlow(_isToogleStart)
    val startDate = MutableStateFlow<Instant?>(_startDate)
    val currentTime = MutableStateFlow<String?>("00:00:00")
    var timerJob: Job? = null

    fun startOrStopTimer() {
        isToggleStart.value = isToggleStart.value.not()
        _isToogleStart = isToggleStart.value

        if (isToggleStart.value) {
            timerJob?.cancel()
            startDate.value = Clock.System.now()
            _startDate = startDate.value
            currentTime.value = "00:00:00"
            timerJob = viewModelScope.launch {
                while (true) {
                    delay(250)
                    val currentDateTime = Clock.System.now()
                    val diff = currentDateTime.epochSeconds - (startDate.value
                        ?: currentDateTime).epochSeconds
                    val seconds = (diff % 60).toTime()
                    val minutes = (diff / 60 % 60).toTime()
                    val hours = (diff / 3600).toTime()
                    currentTime.value = "${hours}:${minutes}:${seconds}"
                }
            }
        } else {
            timerJob?.cancel()
            startDate.value = null
        }
    }

    private fun Long.toTime(): String {
        return this.toString().let {
            if (it.length == 1) {
                "0" + it
            } else {
                it
            }
        }
    }

    companion object {
        var _isToogleStart = false
        var _startDate: Instant? = null
    }
}
