package ru.alex009.redwoodapp.android.widgets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.redwood.LayoutModifier
import app.cash.redwood.compose.RedwoodContent
import app.cash.redwood.layout.composeui.ComposeUiRedwoodLayoutWidgetFactory
import app.cash.redwood.widget.Widget
import com.skydoves.landscapist.coil.CoilImage
import ru.alex009.redwood.schema.ButtonType
import ru.alex009.redwood.schema.widget.Button
import ru.alex009.redwood.schema.widget.MySuperLazyColumn
import ru.alex009.redwoodapp.android.SchemaWidgetFactories
import ru.alex009.redwoodapp.android.types.ActionButton
import ru.alex009.redwoodapp.android.types.PrimaryButton
import ru.alex009.redwoodapp.android.types.SecondaryButton

class ComposeSuperLazyColumn : MySuperLazyColumn<@Composable () -> Unit> {
    private var _childs: List<@Composable () -> Unit> by mutableStateOf(listOf())
    private val factories = SchemaWidgetFactories(
        RedwoodAppSchema = ComposeWidgetFactory,
        RedwoodLayout = ComposeUiRedwoodLayoutWidgetFactory(),
    )

    override fun childs(childs: List<Widget.Children<() -> Unit>>) {

    }

    override fun childs2(childs: List<@Composable () -> Unit>) {
        _childs = childs
    }

    override var layoutModifiers: LayoutModifier = LayoutModifier

    override val value = @Composable {
        LazyColumn(content = {
            _childs.forEach { composeChild ->
                item {
                    RedwoodContent(factories) {
                        composeChild.invoke()
                    }
                }
            }
        })
    }
}
