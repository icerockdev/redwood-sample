package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.redwood.LayoutModifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.coil.CoilImage
import dev.icerock.moko.resources.desc.image.ImageDescResource
import dev.icerock.moko.resources.desc.image.ImageDescUrl
import dev.icerock.redwood.schema.BannerColumnData
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.widget.BannersColumn
import dev.icerock.redwoodapp.android.theme.Colors
import dev.icerock.redwoodapp.android.theme.TextStyles

class ComposeBannersColumn: BannersColumn<() -> Unit> {

    private var _bannersList by mutableStateOf<List<BannerColumnData>>(listOf())

    override var layoutModifiers: LayoutModifier = LayoutModifier
    @OptIn(ExperimentalPagerApi::class)
    override val value = @Composable {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val state = rememberPagerState()

            HorizontalPager(
                count = _bannersList.size, state = state
            ) { page ->
                val data = _bannersList.get(page)

                Box(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = Color.White,
                        elevation = 0.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            val dataImage = data.image
                            val image: Any = when (dataImage) {
                                is ImageDescResource -> painterResource(dataImage.resource.drawableResId)
                                is ImageDescUrl -> dataImage.url
                                else -> ""
                            }
                            CoilImage(
                                modifier = Modifier
                                    .width(Size.Const(116))
                                    .height(Size.Const(116))
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(color = Colors.white)
                                    .clickable {
                                        data.onClick.invoke()
                                    },
                                imageModel = image,
                                placeHolder = data.placeholder?.let { painterResource(it.drawableResId) },
                                contentDescription = ""
                            )
                            Column(
                                modifier = Modifier.padding(start = 16.dp)
                            ) {
                                Text(
                                    text = data.textTitle,
                                    style = TextStyles.primary,
                                    fontSize = 15.sp,
                                    lineHeight = 16.sp,
                                    maxLines = 2
                                )
                                Text(
                                    modifier = Modifier.padding(top = 4.dp),
                                    color = Colors.gray90,
                                    text = data.textDescription,
                                    style = TextStyles.secondarySmall,
                                    fontSize = 13.sp
                                )
                                Text(
                                    modifier = Modifier.padding(top = 4.dp),
                                    text = data.data,
                                    style = TextStyles.caption,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
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

    override fun bannersList(bannersList: List<BannerColumnData>) {
        _bannersList = bannersList
    }


}