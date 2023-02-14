package dev.icerock.redwoodapp.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.redwood.LayoutModifier
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
import dev.icerock.redwood.schema.compose.Image
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.Box
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

    Box {
        Column(horizontalAlignment = CrossAxisAlignment.Center) {
            val isStarted by viewModel.isToggleStart.collectAsState()

            Image(
                120,
                120,
                placeholder = if (isStarted) MR.images.on else MR.images.of,
                layoutModifier = LayoutModifier.padding(Padding(bottom = 25)),
                url = null
            )
            val currentTime by viewModel.currentTime.collectAsState()
            if (isStarted) {
                Text(currentTime ?: "00:00:00", textType = TextType.Header)
            } else {
                Text(
                    "Запустите таймер для начала",
                    textType = TextType.Primary,
                    layoutModifier = LayoutModifier.padding(Padding(horizontal = 16))
                )
                Text(
                    "отслеживания рабочего времени",
                    textType = TextType.Primary,
                    layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 2))
                )
            }


            Button(
                layoutModifier = LayoutModifier.padding(
                    Padding(
                        top = 25,
                        start = 16, end = 16
                    )
                ),
                text = if (isStarted.not()) "Запустить таймер".desc()
                else "Остановить таймер".desc(),
                buttonType = if (isStarted) ButtonType.Secondary else ButtonType.Primary,
                width = Size.Fill,
                onClick = {
                    viewModel.startOrStopTimer()
                }
            )
        }
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
