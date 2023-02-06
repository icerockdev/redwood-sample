package dev.icerock.redwoodapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.protocol.Event
import app.cash.redwood.treehouse.DiffSinkService
import app.cash.redwood.treehouse.FlowWithInitialValue
import app.cash.redwood.treehouse.HostConfiguration
import app.cash.redwood.treehouse.ZiplineTreehouseUi
import app.cash.redwood.treehouse.lazylayout.api.LazyListIntervalContent
import app.cash.redwood.treehouse.lazylayout.compose.LazyColumn
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.resources.desc.image.ImageDescUrl
import dev.icerock.redwood.schema.BannerData
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Banners
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.BANNER
import dev.icerock.redwoodapp.BANNER_2
import dev.icerock.redwoodapp.ToolabrArgs
import dev.icerock.redwoodapp.USER_AVATAR
import dev.icerock.redwoodapp.navigation.Navigator
import dev.icerock.redwoodapp.navigation.ScreenSettings

@Composable
fun MarketScreen(navigator: Navigator, screenSettings: ScreenSettings<ToolabrArgs>) {

    LaunchedEffect(screenSettings){
        screenSettings.setToolbarData(ToolabrArgs.Simple("Market".desc()))
    }

    Column {
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