package ru.alex009.redwoodapp

import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.UIViewChildren
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.UIKit.UIAccessibilityIdentificationProtocol
import platform.UIKit.UIView

fun layoutModifier(): LayoutModifier = LayoutModifier

fun createViewChildren(parent: UIView): UIViewChildren = UIViewChildren(parent)

fun createViewChildrenListener(parent: UIView,
insert: (UIView, Int) -> Unit): UIViewChildren = UIViewChildren(parent, insert = insert)

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
