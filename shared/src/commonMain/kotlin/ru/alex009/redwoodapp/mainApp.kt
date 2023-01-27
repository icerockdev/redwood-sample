package ru.alex009.redwoodapp

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import dev.icerock.moko.resources.desc.desc
import org.example.library.MR
import ru.alex009.redwood.schema.ButtonType
import ru.alex009.redwood.schema.compose.Button
import ru.alex009.redwood.schema.compose.Text
import ru.alex009.redwoodapp.navigation.NavigationHost
import ru.alex009.redwoodapp.navigation.navigation
import ru.alex009.redwoodapp.navigation.navigationTabs

fun mainApp(): NavigationHost {
    return navigation(startDestination = "login") {
        registerScreen(
            uri = "login",
            isToolbarVisible = false
        ) { navigator, _ ->
            LoginScreen(navigator)
        }
        registerNavigation(
            uri = "tabs",
            isToolbarVisible = false,
            childNavigation = { _, _ ->
                mainScreenNavigation()
            }
        )
    }
}

private fun mainScreenNavigation(): NavigationHost = navigationTabs(startDestination = "line") {
    registerNavigation(
        uri = "line",
        title = "Лента".desc(),
        icon = MR.images.line,
        childNavigation = {
            secondTabNavigation()
        }
    )
    registerScreen(
        uri = "settings",
        title = "Настройки".desc(),
        icon = MR.images.settings,
        screen = {
            ProfileScreen(it)
        }
    )
}

private fun secondTabNavigation() = navigation(startDestination = "start") {
    registerScreen(
        "start",
        isToolbarVisible = false,
    ) { navigator, _ ->
        PostsList { date, text ->
            navigator.navigate("/details/${date}?description=${text}")
        }
    }
    registerScreen(
        "/details/{date}?description={description}",
        isToolbarVisible = true
    ) { navController, args ->
        DetailsScreen(
            navController,
            args.get("date").orEmpty(),
            args.get("description").orEmpty()
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
