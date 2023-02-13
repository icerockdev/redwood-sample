package dev.icerock.redwoodapp.screens.market

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.protocol.Event
import app.cash.redwood.treehouse.DiffSinkService
import app.cash.redwood.treehouse.FlowWithInitialValue
import app.cash.redwood.treehouse.HostConfiguration
import app.cash.redwood.treehouse.ZiplineTreehouseUi
import app.cash.redwood.treehouse.lazylayout.api.LazyListIntervalContent
import dev.icerock.moko.fields.flow.FormField
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.resources.desc.image.ImageDescUrl
import dev.icerock.redwood.schema.BannerData
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Banners
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Card
import dev.icerock.redwood.schema.compose.CounterButton
import dev.icerock.redwood.schema.compose.ProductCard
import dev.icerock.redwood.schema.compose.RowWithWeight
import dev.icerock.redwood.schema.compose.Space
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.BANNER
import dev.icerock.redwoodapp.BANNER_2
import dev.icerock.redwoodapp.ToolabrArgs
import dev.icerock.redwoodapp.ToolbarAction
import dev.icerock.redwoodapp.ToolbarButton
import dev.icerock.redwoodapp.USER_AVATAR
import dev.icerock.redwoodapp.ViewModelOwner
import dev.icerock.redwoodapp.getViewModel
import dev.icerock.redwoodapp.navigation.Navigator
import dev.icerock.redwoodapp.navigation.ScreenSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.example.library.MR

@Composable
fun MarketScreen(
    navigator: Navigator,
    screenSettings: ScreenSettings<ToolabrArgs>,
    viewModelOwner: ViewModelOwner
) {

    val viewModel: MarketViewModel = getViewModel(viewModelOwner) {
        MarketViewModel()
    }

    val badgeCount by viewModel.badge.collectAsState()

    LaunchedEffect(screenSettings, badgeCount) {
        screenSettings.setToolbarData(
            ToolabrArgs.Search(
                search = viewModel.searchField,
                placeholder = "Placeholder".desc(),
                actoin = ToolbarAction(
                    icon = MR.images.icon,
                    badge = badgeCount,
                    onCLick = viewModel::onNootificationClick
                ),
                leftButton = ToolbarButton(
                    title = "left button".desc(),
                    icon = MR.images.icon,
                    onCLick = {}
                ),
                rightButton = ToolbarButton(
                    title = "right button".desc(),
                    icon = MR.images.icon,
                    onCLick = {}
                )
            )
        )
    }

    Column(overflow = Overflow.Scroll) {
        val isBannersVisible by viewModel.isBannerVisible.collectAsState()
        if (isBannersVisible)
            Banners(
                layoutModifier = LayoutModifier.padding(Padding(top = 16)),
                bannersList = listOf(
                    BannerData(
                        text = " ".desc(),
                        placeholder = null,
                        image = ImageDescUrl(BANNER)
                    ) {
                        navigator.navigate("login")
                    },
                    BannerData(
                        text = " ".desc(),
                        placeholder = null,
                        image = ImageDescUrl(BANNER_2)
                    ) {
                        navigator.navigate("login")
                    },
                    BannerData(
                        text = " ".desc(),
                        placeholder = null,
                        image = ImageDescUrl(USER_AVATAR)
                    ) {
                        navigator.navigate("login")
                    }
                )
            )

        Text(
            text = "Products",
            textType = TextType.Header,
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16)),
        )

        val productlList by viewModel.productList.collectAsState(listOf())
        productlList.firstOrNull()?.render(viewModel)
        Card(child = {
            productlList.firstOrNull()?.render(viewModel)
        })
        RowWithWeight {
            productlList.firstOrNull()?.render(viewModel)

        }
            productlList.forEachIndexed { index, product ->
            if (index % 2 != 0) return@forEachIndexed
            RowWithWeight {
                product.render(viewModel)
                val secondProduct = productlList.getOrNull(index + 1)
                if (secondProduct == null) {
                    // todo fix
                    Text("")
                } else {
                    secondProduct.render(viewModel)
                }
            }
        }
    }


    // LazyColumn(
    //     intervals = listOf<LazyListIntervalContent>(
    //         LazyListIntervalContent(
    //             count = 10,
    //             itemProvider = LazyColumnContent()
    //         )
    //     )
    // )


}

@Composable
fun Product.render(viewModel: MarketViewModel) {
    ProductCard(
        title = title.desc(),
        image = imageUrl,
        isLiked = isFavorite,
        badge = badge?.desc(),
        price = cost.desc(),
        oldPrice = oldCost?.desc(),
        subtitle = subtitle?.desc(),
        footer = date?.desc(),
        action = {
            if (countInCart == 0) {
                Button(
                    text = "Add".desc(),
                    buttonType = ButtonType.Tonal,
                    enabled = true,
                    width = Size.Fill,
                    onClick = { viewModel.addToCart(id) }
                )
            } else {
                CounterButton(
                    count = countInCart.toString().desc(),
                    width = Size.Fill,
                    onAddClick = { viewModel.addToCart(id) },
                    onRemoveClick = { viewModel.removeFromCard(id) }
                )
            }
        },
        onLikeClick = {
            viewModel.onFavoriteClick(id)
        }
    )
}

class LazyColumnContent : LazyListIntervalContent.Item {
    override fun get(index: Int): ZiplineTreehouseUi {
        return object : ZiplineTreehouseUi {
            override fun sendEvent(event: Event) {
                TODO("Not yet implemented")
            }

            override fun start(
                diffSink: DiffSinkService,
                hostConfigurations: FlowWithInitialValue<HostConfiguration>
            ) {
                TODO("Not yet implemented")
            }

        }
    }

    @Composable
    fun render() {
        Text("text")
    }
}

