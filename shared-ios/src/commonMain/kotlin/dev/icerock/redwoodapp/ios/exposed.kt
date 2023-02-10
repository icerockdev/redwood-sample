package dev.icerock.redwoodapp.ios

import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.Padding
import app.cash.redwood.layout.uiview.UIViewRedwoodLayoutWidgetFactory
import app.cash.redwood.widget.MutableListChildren
import app.cash.redwood.widget.UIViewChildren
import app.cash.redwood.widget.Widget
import dev.icerock.redwood.schema.Size
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.UIKit.UIAccessibilityIdentificationProtocol
import platform.UIKit.UIView
import dev.icerock.redwood.schema.widget.RedwoodAppSchemaWidgetFactories
import dev.icerock.redwood.schema.widget.RedwoodAppSchemaWidgetFactory
import dev.icerock.redwoodapp.ToolabrArgs
import dev.icerock.redwoodapp.navigation.FlatNavigationFactory
import platform.UIKit.invalidateIntrinsicContentSize
import platform.UIKit.removeFromSuperview
import platform.UIKit.setNeedsDisplay
import platform.UIKit.subviews

fun layoutModifier(): LayoutModifier = LayoutModifier

fun createViewChildren(parent: UIView): UIViewChildren = UIViewChildren(parent)

fun createViewChildrenListener(
    parent: UIView,
    insert: (UIView, Int) -> Unit
): UIViewChildren = UIViewChildren(parent, insert = insert)

fun createViewChildrenWithLayoutParams(
    parent: UIView,
    insert: (UIView, Int, LayoutModifier) -> Unit
): MyUIViewChildren = MyUIViewChildren(parent, insert)


fun mainApp(flatNavigationFactory: FlatNavigationFactory<ToolabrArgs>) =
    dev.icerock.redwoodapp.mainApp(flatNavigationFactory)

interface IosFlatNavigationFactory : FlatNavigationFactory<ToolabrArgs>

// todo fix
fun fixMe(arg1: ToolabrArgs.Simple, arg2: ToolabrArgs.NoToolbar) {}
fun fixMe2(arg1: Size.Wrap, arg2: Size.Fill, arg3: Size.Const) {}
fun fixMe(arg1: Padding) {}
typealias MyArgsSimple = ToolabrArgs.Simple
typealias MyArgsNone = ToolabrArgs.NoToolbar

class UIViewWithIdentifier : UIView(frame = CGRectZero.readValue()),
    UIAccessibilityIdentificationProtocol {
    private var _accessibilityIdentifier: String? = null

    override fun accessibilityIdentifier(): String? {
        return _accessibilityIdentifier
    }

    override fun setAccessibilityIdentifier(accessibilityIdentifier: String?) {
        _accessibilityIdentifier = accessibilityIdentifier
    }
}

fun widgetProvider(
    widgetFactory: RedwoodAppSchemaWidgetFactory<UIView>
): RedwoodAppSchemaWidgetFactories<UIView> = RedwoodAppSchemaWidgetFactories(
    RedwoodAppSchema = widgetFactory,
    RedwoodLayout = UIViewRedwoodLayoutWidgetFactory()
)

class MyUIViewChildren(
    private val parent: UIView,
    private val insert: (UIView, Int, LayoutModifier) -> Unit
) : Widget.Children<UIView> {
    private val _widgets = MutableListChildren<UIView>()
    public val widgets: List<Widget<UIView>> get() = _widgets

    override fun insert(index: Int, widget: Widget<UIView>) {
        _widgets.insert(index, widget)
        insert(widget.value, index, widget.layoutModifiers)
        invalidate()
    }

    override fun move(fromIndex: Int, toIndex: Int, count: Int) {
        _widgets.move(fromIndex, toIndex, count)

        val subviews = Array(count) {
            parent.typedSubviews[fromIndex].also(UIView::removeFromSuperview)
        }

        val newIndex = if (toIndex > fromIndex) {
            toIndex - count
        } else {
            toIndex
        }
        subviews.forEachIndexed { offset, view ->
            insert(view, newIndex + offset,_widgets[newIndex+offset].layoutModifiers)
        }
        invalidate()
    }

    override fun remove(index: Int, count: Int) {
        _widgets.remove(index, count)

        repeat(count) {
            parent.typedSubviews[index].removeFromSuperview()
        }
        invalidate()
    }

    override fun onLayoutModifierUpdated(index: Int) {
        invalidate()
    }

    private fun invalidate() {
        parent.setNeedsDisplay()
        parent.invalidateIntrinsicContentSize()
    }
}

@Suppress("UNCHECKED_CAST")
internal val UIView.typedSubviews: List<UIView>
    get() = subviews as List<UIView>
