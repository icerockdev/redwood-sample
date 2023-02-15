package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.compose.ComposeWidgetChildren
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dev.icerock.redwood.schema.widget.Onboarding
import dev.icerock.redwoodapp.android.R
import dev.icerock.redwoodapp.android.theme.Colors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import dev.icerock.moko.resources.ImageResource
import dev.icerock.redwoodapp.android.types.PrimaryButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
class ComposeOnboarding : Onboarding<@Composable () -> Unit> {
    override var layoutModifiers: LayoutModifier = LayoutModifier
    private var onFinishClick: () -> Unit by mutableStateOf({ })
    override val value = @Composable {

        Box() {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val state = rememberPagerState()

                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp)),
                    count = childs.widgets.size, state = state
                ) { page ->
                    val data = childs.widgets.get(page)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        data.value.invoke()
                    }

                }

                Spacer(modifier = Modifier.padding(4.dp))

                DotsIndicator(
                    totalDots = childs.widgets.size,
                    selectedIndex = state.currentPage,
                    selectedColor = Colors.primary,
                    unSelectedColor = Colors.black18
                )

                val coroutineScope = rememberCoroutineScope()
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "Далее", enabled = true, icon = null
                ) {
                    if(state.currentPage == childs.widgets.size -1) {
                        onFinishClick()
                    }else {
                        coroutineScope.launch {
                            state.scrollToPage(state.currentPage + 1)
                        }
                    }
                }
            }
            IconButton(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(40.dp)
                .clip(CircleShape),
                onClick = { onFinishClick() }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_close_24),
                    contentDescription = ""
                )
            }
        }
    }

    override val childs = ComposeWidgetChildren()

    override fun onFinishClick(onFinishClick: (() -> Unit)?) {
        this.onFinishClick = onFinishClick ?: {}
    }

}