package dev.icerock.redwoodapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.collection.mutableVectorOf
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
import app.cash.redwood.treehouse.lazylayout.compose.LazyColumn
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
import dev.icerock.redwood.schema.compose.ProductCard
import dev.icerock.redwood.schema.compose.RowWithWeight
import dev.icerock.redwood.schema.compose.ShortButton
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.BANNER
import dev.icerock.redwoodapp.BANNER_2
import dev.icerock.redwoodapp.SimpleLoginViewModel
import dev.icerock.redwoodapp.ToolabrArgs
import dev.icerock.redwoodapp.ToolbarAction
import dev.icerock.redwoodapp.ToolbarButton
import dev.icerock.redwoodapp.USER_AVATAR
import dev.icerock.redwoodapp.ViewModelOwner
import dev.icerock.redwoodapp.getViewModel
import dev.icerock.redwoodapp.navigation.Navigator
import dev.icerock.redwoodapp.navigation.ScreenSettings
import kotlinx.coroutines.flow.Flow
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
                placeholder = "placegolder".desc(),
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

        RowWithWeight(
            childs = {
                var isLiked: Boolean by remember { mutableStateOf(false) }
                ProductCard(layoutModifier = LayoutModifier.padding(
                    Padding(
                        start = 4,
                        end = 4,
                        bottom = 16,
                        top = 8
                    )
                ),
                    isLiked = isLiked,
                    title = "Product".desc(),
                    image = USER_AVATAR,
                    badge = "50%".desc(),
                    price = "8000$".desc(),
                    oldPrice = "16000$".desc(),
                    subtitle = null,
                    footer = null,
                    onLikeClick = { isLiked = isLiked.not() }) {
                    Button(
                        text = "Text lable".desc(),
                        buttonType = ButtonType.Tonal,
                        enabled = true
                    )
                }
                var isLiked2: Boolean by remember { mutableStateOf(false) }
                ProductCard(layoutModifier = LayoutModifier.padding(
                    Padding(
                        start = 4,
                        end = 4,
                        bottom = 16,
                        top = 8
                    )
                ),
                    isLiked = isLiked2,
                    title = "Product".desc(),
                    image = USER_AVATAR,
                    badge = null,
                    price = "8000$".desc(),
                    oldPrice = null,
                    subtitle = null,
                    footer = null,
                    onLikeClick = { isLiked2 = isLiked2.not() }) {
                    Button(
                        text = "Text lable".desc(),
                        buttonType = ButtonType.Tonal,
                        enabled = true
                    )
                }
            },

            )

        RowWithWeight(
            childs = {
                var isLiked: Boolean by remember { mutableStateOf(false) }
                ProductCard(layoutModifier = LayoutModifier.padding(
                    Padding(
                        start = 4,
                        end = 4,
                        bottom = 16,
                        top = 8
                    )
                ),
                    isLiked = isLiked,
                    title = "Product".desc(),
                    image = USER_AVATAR,
                    badge = "50%".desc(),
                    price = "8000$".desc(),
                    oldPrice = "16000$".desc(),
                    subtitle = "нет в наличии".desc(),
                    footer = "Доставка 14.12.23".desc(),
                    onLikeClick = { isLiked = isLiked.not() }) {
                    Button(
                        text = "Text lable".desc(),
                        buttonType = ButtonType.Tonal,
                        enabled = true
                    )
                }
                var isLiked2: Boolean by remember { mutableStateOf(false) }
                ProductCard(layoutModifier = LayoutModifier.padding(
                    Padding(
                        start = 4,
                        end = 4,
                        bottom = 16,
                        top = 8
                    )
                ),
                    isLiked = isLiked2,
                    title = "Product".desc(),
                    image = USER_AVATAR,
                    badge = null,
                    price = "8000$".desc(),
                    oldPrice = null,
                    subtitle = null,
                    footer = null,
                    onLikeClick = { isLiked2 = isLiked2.not() }) {
                    Button(
                        text = "Text lable".desc(),
                        buttonType = ButtonType.Tonal,
                        enabled = true
                    )
                }
            },

            )

        RowWithWeight(
            childs = {
                Button(
                    layoutModifier = LayoutModifier.padding(
                        Padding(
                            start = 16,
                            end = 8,
                            bottom = 16,
                            top = 8
                        )
                    ),
                    text = "Text lable".desc(),
                    buttonType = ButtonType.Primary,
                    enabled = false
                )
                Button(
                    layoutModifier = LayoutModifier.padding(
                        Padding(
                            start = 8,
                            end = 16,
                            bottom = 16,
                            top = 8
                        )
                    ),
                    text = "Text lable".desc(),
                    buttonType = ButtonType.Primary,
                    enabled = true
                )
            },
        )

        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Primary,
            width = Size.Fill,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Primary,
            width = Size.Fill,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Secondary,
            width = Size.Fill,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Secondary,
            width = Size.Fill,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Text,
            width = Size.Fill,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Text,
            width = Size.Fill,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Tonal,
            width = Size.Fill,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Tonal,
            width = Size.Fill,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Primary,
            width = Size.Fill,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Primary,
            width = Size.Fill,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Secondary,
            width = Size.Fill,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Secondary,
            width = Size.Fill,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Text,
            width = Size.Fill,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Text,
            width = Size.Fill,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Tonal,
            width = Size.Fill,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Tonal,
            width = Size.Fill,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Primary,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Primary,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Secondary,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Secondary,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Text,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Text,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Tonal,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            buttonType = ButtonType.Tonal,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Primary,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Primary,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Secondary,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Secondary,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Text,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Text,
            enabled = false
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Tonal,
            enabled = true
        )
        Button(
            layoutModifier = LayoutModifier.padding(Padding(horizontal = 16, vertical = 8)),
            text = "Text lable".desc(),
            icon = MR.images.icon,
            buttonType = ButtonType.Tonal,
            enabled = false
        )

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

class MarketViewModel() : ViewModel() {
    val searchField = FormField<String, StringDesc?>(
        viewModelScope,
        "",
    ) { input -> input.map { null } }

    private val _badge = MutableStateFlow<Int?>(null)
    val badge: StateFlow<StringDesc?> =
        _badge.map { it?.toString()?.desc() }
            .stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun onNootificationClick() {
        _badge.value = (_badge.value ?: 0) + 1
    }
}