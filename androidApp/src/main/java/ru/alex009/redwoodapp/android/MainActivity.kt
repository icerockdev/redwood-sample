package ru.alex009.redwoodapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.layout.api.Overflow
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.composeui.ComposeUiRedwoodLayoutWidgetFactory
import app.cash.redwood.protocol.widget.ProtocolBridge
import app.cash.redwood.treehouse.AppService
import app.cash.redwood.treehouse.HostConfiguration
import app.cash.redwood.treehouse.TreehouseApp
import app.cash.redwood.treehouse.TreehouseView
import app.cash.redwood.widget.compose.ComposeWidgetChildren
import kotlinx.coroutines.flow.MutableStateFlow
import ru.alex009.redwoodapp.ColumnProvider
import ru.alex009.redwoodapp.HelloWorld
import ru.alex009.redwoodapp.SecondScreen
import ru.alex009.redwoodapp.android.widgets.ComposeWidgetFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factories = SchemaWidgetFactories(
            RedwoodAppSchema = ComposeWidgetFactory,
            RedwoodLayout = ComposeUiRedwoodLayoutWidgetFactory(),
        )

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RedwoodContent(factories) {
                        SecondScreen()
                        //HelloWorld(TruncatingColumnProvider)
                    }
                }
            }
        }
    }
}

private object TruncatingColumnProvider : ColumnProvider {
    @Composable
    override fun <T> create(
        itemsList: List<T>,
        itemContent: @Composable (item: T) -> Unit,
    ) {
        /*LazyColumn {
            items(itemsList) {
                itemContent(it)
            }
        }*/
        Column {
            for (item in itemsList) {
                itemContent(item)
            }
        }
    }
}

/*private class LazyColumnProvider(
    private val bridge: ProtocolBridge,
) : ColumnProvider {
    @Composable
    override fun <T> create(
        items: List<T>,
        itemContent: @Composable (item: T) -> Unit,
    ) {
        bridge.LazyColumn {
            items(items) { item ->
                itemContent(item)
            }
        }
    }
}*/
