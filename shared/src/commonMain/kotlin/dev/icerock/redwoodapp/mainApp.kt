package dev.icerock.redwoodapp

import androidx.compose.runtime.Composable
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.resources.desc.desc
import org.example.library.MR
import dev.icerock.redwoodapp.navigation.NavigationHost
import dev.icerock.redwoodapp.navigation.Navigator
import dev.icerock.redwoodapp.navigation.navigation
import dev.icerock.redwoodapp.navigation.navigationTabs
import dev.icerock.redwoodapp.screens.DetailsScreen
import dev.icerock.redwoodapp.screens.LoginScreen
import dev.icerock.redwoodapp.screens.PostsList
import dev.icerock.redwoodapp.screens.ProfileScreen

fun mainApp(): NavigationHost {
    return navigation(startDestination = "login") {
        registerScreen(
            uri = "login",
            isToolbarVisible = false
        ) { navigator, _, _ ->
            LoginScreen(navigator)
        }
        registerNavigation(
            uri = "tabs",
            isToolbarVisible = false,
            childNavigation = { navigator, _, _ ->
                mainScreenNavigation(navigator)
            }
        )
    }
}

private fun mainScreenNavigation(rootNavigator: Navigator): NavigationHost =
    navigationTabs(startDestination = "line") {
        registerNavigation(
            uri = "line",
            title = MR.strings.tab_list.desc(),
            icon = MR.images.line,
            childNavigation = {
                secondTabNavigation()
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

private fun secondTabNavigation() = navigation(startDestination = "start") {
    registerScreen(
        "start",
        isToolbarVisible = true,
    ) { navigator, _, screenSettings ->
        PostsList(screenSettings) { date, text ->
            navigator.navigate("/details/${date}?description=${text}")
        }
    }
    registerScreen(
        "/details/{date}?description={description}",
        isToolbarVisible = true
    ) { navController, args, screenSettings ->
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
