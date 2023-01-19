package ru.alex009.redwoodapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.redwood.compose.LocalWidgetVersion
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.layout.composeui.ComposeUiRedwoodLayoutWidgetFactory
import app.cash.redwood.protocol.widget.ProtocolBridge
import app.cash.redwood.treehouse.ZiplineTreehouseUi
import app.cash.redwood.treehouse.lazylayout.api.LazyListIntervalContent
import ru.alex009.redwoodapp.ColumnProvider
import ru.alex009.redwoodapp.HelloWorld
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
                Column() {
                    LazyColumn(modifier = Modifier.height(100.dp)){
                        item { Text("text") }
                    }
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        RedwoodContent(factories) {
                        }
                    }
                }
            }
        }
    }
}