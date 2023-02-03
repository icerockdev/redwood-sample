package dev.icerock.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.navigation.FlatNavigationFactory
import org.example.library.MR
import dev.icerock.redwoodapp.navigation.NavigationHost
import dev.icerock.redwoodapp.navigation.Navigator
import dev.icerock.redwoodapp.navigation.navigation
import dev.icerock.redwoodapp.navigation.navigationTabs
import dev.icerock.redwoodapp.screens.DetailsScreen
import dev.icerock.redwoodapp.screens.LoginScreen
import dev.icerock.redwoodapp.screens.PostsList
import dev.icerock.redwoodapp.screens.ProfileScreen
import kotlinx.coroutines.flow.Flow

fun mainApp(flatNavigationFactory: FlatNavigationFactory<ToolabrArgs>): NavigationHost {
    return navigation(startDestination = "login", flatNavigationFactory) {
        registerScreen(
            uri = "login"
        ) { navigator, _, screenSettings, viewModelOwner ->
            LoginScreen(navigator, screenSettings, viewModelOwner)
        }
        registerScreen(
            uri = "tabs"
        ) { navigator, _, screenSettings, viewModelOwner ->
            Box {
                LaunchedEffect(screenSettings){
                    screenSettings.setToolbarData(ToolabrArgs.Simple("SecondScreen".desc()))
                }
                Column (horizontalAlignment = CrossAxisAlignment.Center){
                    Text("SecondScreem")
                    Button("Next Screen".desc(), onClick = {
                        navigator.navigate( "3") },
                    buttonType = ButtonType.Primary,
                    layoutModifier = LayoutModifier.padding(Padding(16)))
                }
            }
        }
        registerScreen(
            uri = "3"
        ) { navigator, _, screenSettings, viewModelOwner ->
            Box {
                LaunchedEffect(screenSettings){
                    screenSettings.setToolbarData(ToolabrArgs.NoToolbar)
                }
                Column {
                    Text("No toolbar screen")
                }
            }
        }
        registerNavigation(
            uri = "sdsd",
            isToolbarVisible = false,
            childNavigation = { navigator, _, _, _ ->
                mainScreenNavigation(navigator, flatNavigationFactory)
            }
        )
    }
}

private fun mainScreenNavigation(
    rootNavigator: Navigator,
    flatNavigationFactory: FlatNavigationFactory<ToolabrArgs>
): NavigationHost =
    navigationTabs(startDestination = "line") {
        registerNavigation(
            uri = "line",
            title = MR.strings.tab_list.desc(),
            icon = MR.images.line,
            childNavigation = {
                secondTabNavigation(flatNavigationFactory = flatNavigationFactory)
            }
        )
        registerScreen(
            uri = "settings",
            title = MR.strings.tab_settings.desc(),
            icon = MR.images.settings,
            screen = {
                ProfileScreen(rootNavigator)
            }
        )
    }

private fun secondTabNavigation(flatNavigationFactory: FlatNavigationFactory<ToolabrArgs>) =
    navigation(startDestination = "start", flatNavigationFactory) {
        registerScreen(
            "start",
            isToolbarVisible = true,
        ) { navigator, _, screenSettings, viweModelOwner ->
            PostsList(screenSettings, viweModelOwner) { date, text ->
                navigator.navigate("/details/${date}?description=${text}")
            }
        }
        registerScreen(
            "/details/{date}?description={description}",
            isToolbarVisible = true
        ) { navController, args, screenSettings, _ ->
            DetailsScreen(
                navController,
                args["date"].orEmpty(),
                args["description"].orEmpty(),
                screenSettings
            )
        }
    }

@Composable
fun Box(content: @Composable () -> Unit) {
    Column(
        horizontalAlignment = CrossAxisAlignment.Center,
        verticalAlignment = MainAxisAlignment.Center,
        height = Constraint.Fill
    ) {
        Row(
            verticalAlignment = CrossAxisAlignment.Center,
            horizontalAlignment = MainAxisAlignment.Center,
            width = Constraint.Fill
        ) {
            content()
        }
    }
}

sealed class ToolabrArgs {
    data class Simple(
        val title: StringDesc,
        val actoins: List<ToolbarAction> = listOf()
    ) : ToolabrArgs()

    object NoToolbar : ToolabrArgs()
}

data class ToolbarAction(
    val icon: ImageResource,
    val badge: StringDesc?,
    val onCLick: ()->Unit
)