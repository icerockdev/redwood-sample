package dev.icerock.redwoodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.fields.flow.FormField
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.schema.ButtonType
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.compose.Button
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.navigation.FlatNavigationFactory
import org.example.library.MR
import dev.icerock.redwoodapp.navigation.NavigationHost
import dev.icerock.redwoodapp.navigation.Navigator
import dev.icerock.redwoodapp.navigation.navigation
import dev.icerock.redwoodapp.navigation.navigationTabs
import dev.icerock.redwoodapp.screens.DetailsScreen
import dev.icerock.redwoodapp.screens.demo.LoginScreen
import dev.icerock.redwoodapp.screens.market.MarketScreen
import dev.icerock.redwoodapp.screens.PostsList
import dev.icerock.redwoodapp.screens.demo.ProfileScreen
import dev.icerock.redwoodapp.screens.demo.TestCompleteScreen
import dev.icerock.redwoodapp.screens.demo.TestListScreen
import dev.icerock.redwoodapp.screens.demo.TestStep
import dev.icerock.redwoodapp.screens.demo.TestStepScreen
import dev.icerock.redwoodapp.screens.demo.ToogleScreen
import dev.icerock.redwoodapp.screens.demo.navigation.Screens

fun mainApp(flatNavigationFactory: FlatNavigationFactory<ToolabrArgs>): NavigationHost {
    return navigation(startDestination =  Screens.LOGIN, flatNavigationFactory) {
        registerScreen(
            uri = Screens.LOGIN
        ) { navigator, _, screenSettings, viewModelOwner ->
            LoginScreen(navigator, screenSettings, viewModelOwner)
        }
        registerNavigation(
            uri = Screens.TABS,
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
    navigationTabs(startDestination = Screens.TEST_LIST) {
        registerNavigation(
            uri = Screens.TEST_LIST,
            title = MR.strings.tab_list.desc(),
            icon = MR.images.list,
            childNavigation = {
                secondTabNavigation(
                    tabNavController = it,
                    flatNavigationFactory = flatNavigationFactory)
            }
        )
        registerNavigation(
            uri = Screens.TOGGLE,
            title = MR.strings.tab_toggle.desc(),
            icon = MR.images.toggl,
            childNavigation = {
                navigation(startDestination = Screens.TOGGLE, flatNavigationFactory) {
                    registerScreen(
                        Screens.TOGGLE,
                        isToolbarVisible = true,
                    ) { navigator, _, screenSettings, viweModelOwner ->
                        ToogleScreen(screenSettings, viweModelOwner)
                    }
                }
            }
        )
        registerScreen(
            uri = Screens.PROFILE,
            title = MR.strings.tab_settings.desc(),
            icon = MR.images.profile,
            screen = {
                ProfileScreen(rootNavigator)
            }
        )
    }

private fun secondTabNavigation(tabNavController: Navigator, flatNavigationFactory: FlatNavigationFactory<ToolabrArgs>) =
    navigation(startDestination = Screens.TEST_LIST, flatNavigationFactory) {
        registerScreen(
            Screens.TEST_LIST,
            isToolbarVisible = true,
        ) { navigator, _, screenSettings, viweModelOwner ->
            TestListScreen(navigator, screenSettings, viweModelOwner)
        }
        registerScreen(
            Screens.TEST_STEP,
            isToolbarVisible = true
        ) { navController, args, screenSettings, viweModelOwner ->
            TestStepScreen(
                navController,
                args["testId"]?.toInt()?:0,
                screenSettings,
                viweModelOwner
            )
        }
        registerScreen(
            Screens.TEST_FINAL,
            isToolbarVisible = true
        ) { navController, args, screenSettings, _ ->
            TestCompleteScreen(
                tabNavController,
                navController,
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

    data class Search(
        val search: FormField<String, StringDesc?>,
        val placeholder: StringDesc?,
        val actoin: ToolbarAction,
        val leftButton: ToolbarButton?,
        val rightButton: ToolbarButton?,
    ) : ToolabrArgs()

    object NoToolbar : ToolabrArgs()
}

data class ToolbarButton(
    val title: StringDesc,
    val icon: ImageResource,
    val onCLick: () -> Unit
)

data class ToolbarAction(
    val icon: ImageResource,
    val badge: StringDesc?,
    val onCLick: () -> Unit
)