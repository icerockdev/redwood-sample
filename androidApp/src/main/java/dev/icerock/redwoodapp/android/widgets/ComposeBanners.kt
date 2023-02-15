package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.coil.CoilImage
import dev.icerock.moko.resources.desc.image.ImageDescResource
import dev.icerock.moko.resources.desc.image.ImageDescUrl
import dev.icerock.redwood.schema.BannerData
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.widget.Banners
import dev.icerock.redwood.schema.widget.Image
import dev.icerock.redwoodapp.android.theme.Colors

@OptIn(ExperimentalPagerApi::class)
class ComposeBanners : Banners<() -> Unit> {

    var bannersState by mutableStateOf<List<BannerData>>(listOf())

    override fun bannersList(bannersList: List<BannerData>) {
        bannersState = bannersList
    }

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val state = rememberPagerState()

            HorizontalPager(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    )
                    .clip(RoundedCornerShape(16.dp)),
                count = bannersState.size, state = state
            ) { page ->
                val data = bannersState.get(page)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(156.dp)
                ) {
                    val dataImage = data.image
                    val image: Any = when (dataImage) {
                        is ImageDescResource -> painterResource(dataImage.resource.drawableResId)
                        is ImageDescUrl -> dataImage.url
                        else -> ""
                    }
                    CoilImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(color = Colors.black18)
                            .clickable {
                                data.onClick.invoke()
                            },
                        imageModel = image,
                        placeHolder = data.placeholder?.let { painterResource(it.drawableResId) },
                        contentDescription = ""
                    )

                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = data.text.toString(LocalContext.current)
                    )
                }

            }

            Spacer(modifier = Modifier.padding(4.dp))

            DotsIndicator(
                totalDots = 3,
                selectedIndex = state.currentPage,
                selectedColor = Colors.primary,
                unSelectedColor = Colors.black18
            )
        }

    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {

    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color = selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}