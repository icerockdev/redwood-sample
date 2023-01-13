package ru.alex009.redwoodapp

import androidx.compose.runtime.BroadcastFrameClock
import app.cash.redwood.compose.RedwoodComposition
import app.cash.redwood.layout.uiview.UIViewRedwoodLayoutWidgetFactory
import app.cash.redwood.widget.UIViewChildren
import kotlinx.cinterop.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.plus
import platform.UIKit.UIStackView
import platform.UIKit.UIView
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactories
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory
import ru.alex009.redwood.schema.widget.Text

@Suppress("unused") // Called from Swift.
class RedwoodViewControllerDelegate(
    root: UIStackView,
    widgetFactory: WidgetFactory
) {
    private val clock = BroadcastFrameClock()
    private val scope: CoroutineScope = MainScope() + clock

    init {
        val children = UIViewChildren(
            parent = root,
            insert = { view, index -> root.insertArrangedSubview(view, atIndex = index.convert()) }
        )
        val composition = RedwoodComposition(
            scope = scope,
            container = children,
            provider = RedwoodAppSchemaWidgetFactories(
                RedwoodAppSchema = widgetFactory,
                RedwoodLayout = UIViewRedwoodLayoutWidgetFactory()
            )
        )
        composition.setContent {
            HelloWorld()
        }
    }

    fun tickClock() {
        clock.sendFrame(0L) // Compose does not use frame time.
    }

    fun dispose() {
        scope.cancel()
    }
}

interface WidgetFactory : RedwoodAppSchemaWidgetFactory<UIView>

@Suppress("unused") // Called from Swift.
interface TextView : Text<UIView>

@Suppress("unused") // Called from Swift.
interface TextInputView : Text<UIView>

@Suppress("unused") // Called from Swift.
interface ImageView : Text<UIView>