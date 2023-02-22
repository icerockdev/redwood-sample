package dev.icerock.redwoodapp.screens.market

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import app.cash.redwood.protocol.Event
import app.cash.redwood.treehouse.DiffSinkService
import app.cash.redwood.treehouse.FlowWithInitialValue
import app.cash.redwood.treehouse.HostConfiguration
import app.cash.redwood.treehouse.ZiplineTreehouseUi
import app.cash.redwood.treehouse.lazylayout.api.LazyListIntervalContent
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.resources.desc.image.ImageDescUrl
import dev.icerock.redwood.navigation.navbar.NavBarController
import dev.icerock.redwood.navigation.navbar.rememberNavBarController
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.navigation.viewmodel.getViewModel
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
import dev.icerock.redwood.schema.compose.Tabs
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.BANNER
import dev.icerock.redwoodapp.BANNER_2
import dev.icerock.redwoodapp.ToolbarAction
import dev.icerock.redwoodapp.ToolbarArgs
import dev.icerock.redwoodapp.ToolbarButton
import dev.icerock.redwoodapp.USER_AVATAR
import dev.icerock.redwoodapp.ext.weight
import org.example.library.MR


/*
@Composable
fun MarketScreen() {
  //  val viewModel: MarketViewModel = getViewModel { MarketViewModel() }
    // val navBarController: NavBarController = rememberNavBarController()
 //   Tabs(listOf("tabd".desc(),"tabs".desc())
 //   ,{}, 0
 //   )
    //  val badgeCount: StringDesc? by viewModel.badge.collectAsState()

    /* LaunchedEffect(navBarController, badgeCount) {
          navBarController.navBarData = ToolbarArgs.Search(
              search = viewModel.searchField,
              placeholder = "Placeholder".desc(),
              action = ToolbarAction(
                  icon = MR.images.icon,
                  badge = badgeCount,
                  onClick = viewModel::onNootificationClick
              ),
              leftButton = ToolbarButton(
                  title = "left button".desc(),
                  icon = MR.images.icon, onClick = {}
              ),
              rightButton = ToolbarButton(
                  title = "right button".desc(),
                  icon = MR.images.icon,
                  onClick = {}
              )
          )
      }*/

    Column(overflow = Overflow.Scroll) {


        val productlList by viewModel.productList.collectAsState(listOf())
        productlList.forEachIndexed { index, product ->
            if (index % 2 != 0) return@forEachIndexed
            Row {
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

@Composable
fun Product.render(viewModel: MarketViewModel) {
    ProductCard(
        layoutModifier = LayoutModifier.weight(1f),
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

*/