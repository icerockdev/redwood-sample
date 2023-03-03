package dev.icerock.redwoodapp.ios

import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.Padding
import app.cash.redwood.layout.uiview.UIViewRedwoodLayoutWidgetFactory
import app.cash.redwood.widget.UIViewChildren
import app.cash.redwood.widget.Widget
import dev.icerock.redwood.navigation.NavigationFactory
import dev.icerock.redwood.schema.Size
import dev.icerock.redwood.schema.widget.RedwoodAppSchemaWidgetFactories
import dev.icerock.redwood.schema.widget.RedwoodAppSchemaWidgetFactory
import dev.icerock.redwoodapp.ToolbarArgs
import dev.icerock.redwoodapp.ext.Weight
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.UIKit.UIAccessibilityIdentificationProtocol
import platform.UIKit.UIView

fun layoutModifier(): LayoutModifier = LayoutModifier

fun createViewChildren(parent: UIView): UIViewChildren = UIViewChildren(parent)

fun createViewChildrenListener(
    parent: UIView,
    insert: (UIView, Int) -> Unit
): UIViewChildren = UIViewChildren(parent, insert = insert)

fun createViewChildrenModifierListener(
    parent: UIView,
    insert: (Widget<UIView>, Int) -> Unit
): UIViewChildrenWithModifier = UIViewChildrenWithModifier(
    parent, insertWidget = insert
)

fun mainApp(navigationFactory: NavigationFactory) =
    dev.icerock.redwoodapp.screens.demo.mainApp(navigationFactory)

//interface IosFlatNavigationFactory : FlatNavigationFactory<ToolbarArgs>

// todo fix
fun fixMe(arg1: ToolbarArgs.Simple, arg2: ToolbarArgs.NoToolbar) {}
fun fixMe2(arg1: Size.Wrap, arg2: Size.Fill, arg3: Size.Const) {}
fun fixMe(arg1: Padding, layoutModifier: Weight) {}
typealias MyArgsSimple = ToolbarArgs.Simple
typealias MyArgsNone = ToolbarArgs.NoToolbar

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


