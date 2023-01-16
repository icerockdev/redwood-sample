package ru.alex009.redwood.schema

import androidx.compose.runtime.Composable
import app.cash.redwood.layout.RedwoodLayout
import app.cash.redwood.schema.Children
import app.cash.redwood.schema.Default
import app.cash.redwood.schema.Property
import app.cash.redwood.schema.Schema
import app.cash.redwood.schema.Schema.Dependency
import app.cash.redwood.schema.Widget

@Schema(
    members = [
        TextInput::class,
        Text::class,
        Image::class,
        Stack::class,
        Card::class,
        ImageButton::class,
        Button::class,
    ],
    dependencies = [
        Dependency(1, RedwoodLayout::class),
    ],
)
interface RedwoodAppSchema

@Widget(1)
data class TextInput(
    @Property(1)
    @Default("\"\"")
    val state: String,
    @Property(2)
    @Default("\"\"")
    val hint: String,
    @Property(3)
    @Default("null")
    val onChange: (String) -> Unit,
)

@Widget(2)
data class Text(
    @Property(1) val text: String,
    @Property(2)
    @Default("false") val isSingleLine: Boolean
)

@Widget(3)
data class Image(
    @Property(1) val url: String,
)

@Widget(4)
data class ImageButton(
    @Property(1) val text: String,
    @Property(2)
    @Default("null")  val icon: String?,
    @Property(3)
    @Default("true")
    val enabled: Boolean,
    @Property(3)
    val onClick: () -> Unit
)

@Widget(5)
data class Button(
    @Property(1) val text: String,
    @Property(2)
    val buttonType: ButtonType,
    @Property(3)
    @Default("true")
    val enabled: Boolean,
    @Property(4)
    val onClick: () -> Unit,

)

@Widget(6)
data class Card(
    @Children(1) val child: @Composable () -> Unit,
)

//todo fix to normal list
@Widget(7)
data class Stack(
    @Children(1) val child1: @Composable () -> Unit,
    @Children(2) val child2: @Composable () -> Unit,
)