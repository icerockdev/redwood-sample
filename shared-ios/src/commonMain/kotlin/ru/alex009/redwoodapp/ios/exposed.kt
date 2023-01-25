package ru.alex009.redwoodapp.ios

import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.uiview.UIViewRedwoodLayoutWidgetFactory
import app.cash.redwood.widget.UIViewChildren
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.UIKit.UIAccessibilityIdentificationProtocol
import platform.UIKit.UIView
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactories
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory

fun layoutModifier(): LayoutModifier = LayoutModifier

fun createViewChildren(parent: UIView): UIViewChildren = UIViewChildren(parent)

fun createViewChildrenListener(
    parent: UIView,
    insert: (UIView, Int) -> Unit
): UIViewChildren = UIViewChildren(parent, insert = insert)

fun mainApp() = ru.alex009.redwoodapp.mainApp()

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
