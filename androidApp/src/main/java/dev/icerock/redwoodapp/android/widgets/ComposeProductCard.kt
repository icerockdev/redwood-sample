package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.compose.ComposeWidgetChildren
import com.skydoves.landscapist.coil.CoilImage
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.widget.Card
import dev.icerock.redwood.schema.widget.ProductCard
import dev.icerock.redwoodapp.USER_AVATAR
import dev.icerock.redwoodapp.android.R
import dev.icerock.redwoodapp.android.theme.Colors

class ComposeProductCard : ProductCard<@Composable () -> Unit> {
    override var layoutModifiers: LayoutModifier = LayoutModifier
    private var _onLikeClick: () -> Unit by mutableStateOf({})
    private var _title: StringDesc? by mutableStateOf(null)
    private var _image: String? by mutableStateOf(null)
    private var _cost: StringDesc? by mutableStateOf(null)
    private var _oldCost: StringDesc? by mutableStateOf(null)
    private var _badge: StringDesc? by mutableStateOf(null)
    private var _isLiked: Boolean by mutableStateOf(false)
    private var _subtitle: StringDesc? by mutableStateOf(null)
    private var _footer: StringDesc? by mutableStateOf(null)

    override val value = @Composable {
        ProductCard(
            _onLikeClick,
            _title,
            _image,
            _cost,
            _oldCost,
            _badge,
            _isLiked,
            _subtitle,
            _footer,
        )
    }

    @Composable
    fun ProductCard(
        onLikeClick: () -> Unit,
        title: StringDesc?,
        image: String?,
        cost: StringDesc?,
        oldCost: StringDesc?,
        badge: StringDesc?,
        isLiked: Boolean,
        subtitle: StringDesc?,
        footer: StringDesc?
    ) {

        Column(modifier = Modifier.padding(bottom = 12.dp)) {
            Box() {
                CoilImage(
                    modifier = Modifier
                        .padding(horizontal = 19.dp, vertical = 6.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(color = Colors.black18),
                    imageModel = _image,
                    placeHolder = painterResource(dev.icerock.redwoodapp.shared.R.drawable.ava_preview),
                    contentDescription = ""
                )
                _badge?.toString(LocalContext.current)?.let {
                    Text(
                        modifier = Modifier
                            .align(
                                Alignment.BottomStart
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                            .clip(CircleShape)
                            .background(Colors.error)
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                        text = it,
                        color = Color.White
                    )
                }
                Image(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .clickable { _onLikeClick.invoke() }
                        .background(Colors.gray60)
                        .padding(8.dp),
                    painter = painterResource(id = if (_isLiked) dev.icerock.redwoodapp.shared.R.drawable.favorite_fill else dev.icerock.redwoodapp.shared.R.drawable.favorite),
                    contentDescription = ""
                )
            }
            Row(modifier = Modifier.padding(start = 12.dp, top = 4.dp)) {
                _cost?.toString(LocalContext.current)?.let {
                    Text(
                        text = it,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                _oldCost?.toString(LocalContext.current)?.let {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            modifier = Modifier
                                .padding(start = 4.dp, top = 2.dp)
                                .drawWithContent {
                                    drawContent()
                                    val strokeWidth = 2.dp.toPx()
                                    val verticalCenter = size.height / (2) + strokeWidth/2
                                    drawLine(
                                        color = Colors.error,
                                        strokeWidth = strokeWidth,
                                        start = Offset(0f, verticalCenter),
                                        end = Offset(size.width.toFloat(), verticalCenter)
                                    )
                                },
                            text = it,
                            fontSize = 15.sp
                        )
                        //Box(
                        //    modifier = Modifier
                        //        .fillMaxWidth()
                        //        .height(1.dp)
                        //        .background(Colors.error)
                        //)
                    }
                }
            }
            _title?.toString(LocalContext.current)?.let {
                Text(
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                    text = it,
                    fontSize = 15.sp,
                )
            }
            _subtitle?.toString(LocalContext.current)?.let {
                Text(
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp), text = it,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Colors.secondary
                )
            }
            Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp)) {
                action.render()
            }
            _footer?.toString(LocalContext.current)?.let {
                Text(
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp), text = it,
                    fontSize = 12.sp,
                    letterSpacing = 0.7.sp
                )
            }
        }
    }

    @Composable
    @Preview
    fun ComposableCardPreview() {
        ProductCard(
            {},
            "title".desc(),
            USER_AVATAR,
            "8000$".desc(),
            "160000".desc(),
            "50%".desc(),
            true,
            "subtitle".desc(),
            "footer".desc()
        )
    }

    override val action = ComposeWidgetChildren()
    override fun title(title: StringDesc) {
        _title = title
    }

    override fun image(image: String) {
        _image = image
    }

    override fun isLiked(isLiked: Boolean) {
        _isLiked = isLiked
    }

    override fun badge(badge: StringDesc?) {
        _badge = badge
    }

    override fun price(price: StringDesc) {
        _cost = price
    }

    override fun oldPrice(oldPrice: StringDesc?) {
        _oldCost = oldPrice
    }

    override fun subtitle(subtitle: StringDesc?) {
        _subtitle = subtitle
    }

    override fun footer(footer: StringDesc?) {
        _footer = footer
    }

    override fun onLikeClick(onLikeClick: (() -> Unit)?) {
        _onLikeClick = onLikeClick ?: {}
    }

}