package dev.icerock.redwoodapp.ios

import app.cash.redwood.LayoutModifier
import app.cash.redwood.layout.Padding
import app.cash.redwood.widget.MutableListChildren
import app.cash.redwood.widget.Widget
import dev.icerock.redwoodapp.ext.Weight
import platform.UIKit.UIView
import platform.UIKit.invalidateIntrinsicContentSize
import platform.UIKit.removeFromSuperview
import platform.UIKit.setNeedsDisplay
import platform.UIKit.subviews

public class UIViewChildrenWithModifier(
    private val parent: UIView,
    private val insertWidget: (Widget<UIView>, Int) -> Unit,
) : Widget.Children<UIView> {
    private val _widgets = MutableListChildren<UIView>()
    public val widgets: List<Widget<UIView>> get() = _widgets

    override fun insert(index: Int, widget: Widget<UIView>) {
        _widgets.insert(index, widget)
        insertWidget(
            widget,
            index
        )
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
        _widgets.forEachIndexed { offset, view ->
            insertWidget(
                view, newIndex + offset
            )
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

val UIView.typedSubviews: List<UIView>
    get() = subviews as List<UIView>


fun LayoutModifier.getWeight(): Weight? {
    var weight: Weight? = null
    var counter = 1
    forEach { element ->
        counter++
        (element as? Weight)?.let {
            weight = it
        }
    }
    return Weight(counter.toFloat())
}

fun LayoutModifier.getPadding(): Padding? {
    var weight: Padding? = null
    forEach {
        if (it is Padding) {
            weight = it
        }
    }
    return weight
}