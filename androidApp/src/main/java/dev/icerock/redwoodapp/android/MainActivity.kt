package dev.icerock.redwoodapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import app.cash.redwood.layout.composeui.ComposeUiRedwoodLayoutWidgetFactory
import dev.icerock.redwoodapp.android.navigation.DefaultNavigationTapbar
import dev.icerock.redwoodapp.android.widgets.ComposeWidgetFactory
import dev.icerock.redwoodapp.mainApp
import dev.icerock.redwoodapp.navigation.NavigationHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factories = SchemaWidgetFactories(
            RedwoodAppSchema = ComposeWidgetFactory,
            RedwoodLayout = ComposeUiRedwoodLayoutWidgetFactory(),
        )

        val app: NavigationHost = mainApp(DefaultNavigationTapbar())

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    app.Render(factories)
                }
            }
        }
    }
}
