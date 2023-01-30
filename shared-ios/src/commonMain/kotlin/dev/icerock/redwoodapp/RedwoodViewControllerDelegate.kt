package dev.icerock.redwoodapp

import platform.UIKit.UIView
import dev.icerock.redwood.schema.widget.RedwoodAppSchemaWidgetFactory
import dev.icerock.redwood.schema.widget.Button
import dev.icerock.redwood.schema.widget.Card
import dev.icerock.redwood.schema.widget.Image
import dev.icerock.redwood.schema.widget.ImageButton
import dev.icerock.redwood.schema.widget.Stack
import dev.icerock.redwood.schema.widget.Text
import dev.icerock.redwood.schema.widget.TextInput

interface WidgetFactory : RedwoodAppSchemaWidgetFactory<UIView>

@Suppress("unused") // Called from Swift.
interface WidgetButton : Button<UIView>

@Suppress("unused") // Called from Swift.
interface WidgetCard : Card<UIView>

@Suppress("unused") // Called from Swift.
interface WidgetImageButton : ImageButton<UIView>

@Suppress("unused") // Called from Swift.
interface WidgetStack : Stack<UIView>

@Suppress("unused") // Called from Swift.
interface WidgetImage : Image<UIView>

@Suppress("unused") // Called from Swift.
interface WidgetText : Text<UIView>

@Suppress("unused") // Called from Swift.
interface WidgetTextInput : TextInput<UIView>
