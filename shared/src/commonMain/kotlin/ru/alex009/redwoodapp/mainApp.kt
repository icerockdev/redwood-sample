package ru.alex009.redwoodapp

import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
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
            Column(verticalAlignment = MainAxisAlignment.End) {
                Column {
                    Button(
                        " Login ", buttonType = ButtonType.Primary,
                        onClick = {
                            navigator.navigate("tabs")
                        }, layoutModifier = LayoutModifier.padding(Padding(16))
                    )
                    Button(
                        " Login ", buttonType = ButtonType.Secondary,
                        onClick = {
                            navigator.navigate("tabs")
                        }, layoutModifier = LayoutModifier.padding(Padding(16))
                    )
                    Button(
                        " Login ", buttonType = ButtonType.Action,
                        onClick = {
                            navigator.navigate("tabs")
                        }, layoutModifier = LayoutModifier.padding(Padding(16))
                    )
                }
            }
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

private fun mainScreenNavigation(): NavigationHost = navigationTabs(startDestination = "auth") {
    registerScreen(
        uri = "auth",
        title = "fistTab".desc(),
        icon = MR.images.ic_favorite_menu,
        screen = {
            Column {
                Text(
                    text = "First tab",
                    layoutModifier = LayoutModifier.padding(Padding(16))
                )
            }
        }
    )
    registerNavigation(
        uri = "auth2",
        title = "secondTab".desc(),
        icon = MR.images.ic_favorite_menu,
        childNavigation = {
            secondTabNavigation()
        }
    )
}

private fun secondTabNavigation() = navigation(startDestination = "start") {
    registerScreen(
        "start",
        isToolbarVisible = false,
    ) { navigator, _ ->
        PostsList {
            navigator.navigate("/user/2/orders?query=dasdsa&order-by=id")
        }
    }
    registerScreen(
        "/user/{userId}/orders",
        isToolbarVisible = true
    ) { _, args ->
        Column {
            Text(
                text = args.toString(),
                layoutModifier = LayoutModifier.padding(
                    Padding(16)
                )
            )
        }
    }
}
