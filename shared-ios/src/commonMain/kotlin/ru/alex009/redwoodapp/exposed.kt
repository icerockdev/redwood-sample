package ru.alex009.redwoodapp

import app.cash.redwood.LayoutModifier
import app.cash.redwood.widget.UIViewChildren
import platform.UIKit.UIView

fun layoutModifier(): LayoutModifier = LayoutModifier

fun createViewChildren(parent: UIView): UIViewChildren = UIViewChildren(parent)
