package ru.alex009.redwoodapp

import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.api.Padding
import app.cash.redwood.layout.compose.Column
import org.example.library.MR
import ru.alex009.redwood.schema.ButtonType
import ru.alex009.redwood.schema.compose.Button
import ru.alex009.redwood.schema.compose.Text
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory

fun mainApp(widgetFactory: RedwoodAppSchemaWidgetFactory<WidgetType>): NavigationRoot {
    return navigation(widgetFactory, "login") {
        register("login") { navigator ->
            Column(verticalAlignment = MainAxisAlignment.End) {
                Column {
                    Button(" Login ", buttonType = ButtonType.Primary,
                        onClick = {
                            navigator.navigate("tabs")
                        }
                    , layoutModifier = LayoutModifier.padding(Padding(16)))
                }
            }
        }
        register("tabs",
            navigationTabs(widgetFactory, "auth") {
                register(
                    "auth",
                    title = "fistTab",
                    icon = MR.images.ic_favorite_menu
                ) { navigator ->
                    Column {
                        Text(
                            "First tab", layoutModifier = LayoutModifier.padding(
                                Padding(16)
                            )
                        )
                    }
                }
                register(
                    "auth2",
                    title = "secondTab",
                    icon = MR.images.ic_favorite_menu,
                    navigation(widgetFactory, "start") {
                        register("start") { navigator ->
                            PostsList {
                                navigator.navigate("second", it)
                            }
                        }
                        registerWithArgs<String>("second") { navigator, args ->
                            Column {
                                Text(
                                    args ?: "No data", layoutModifier = LayoutModifier.padding(
                                        Padding(16)
                                    )
                                )
                            }
                        }
                    }
                )
            }
        )
    }
}
