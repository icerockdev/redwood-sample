package ru.alex009.redwood.schema

import androidx.compose.runtime.Composable
import app.cash.redwood.layout.RedwoodLayout
import app.cash.redwood.schema.Children
import app.cash.redwood.schema.Default
import app.cash.redwood.schema.Property
import app.cash.redwood.schema.Schema
import app.cash.redwood.schema.Schema.Dependency
import app.cash.redwood.schema.Widget
import dev.icerock.moko.resources.ColorResource

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
    @Default("null")
    @Property(4) val textStyle: TextStyle?,
)

@Widget(2)
data class Text(
    @Property(1) val text: String,
    @Property(2)
    @Default("false") val isSingleLine: Boolean,
    @Property(3)
    @Default("null") val textStyle: TextStyle?
)

@Widget(3)
data class Image(
    @Property(1) val url: String,
)

@Widget(7)
data class ImageButton(
    @Property(1) val text: String,
    @Property(2)
    @Default("null")  val icon: String?,
    @Property(3)
    @Default("null")
    val iconPadding: Int?,
    @Property(4)
    @Default("null")
    val textStyle: TextStyle?
)

@Widget(8)
data class Button(
    @Property(1) val text: String,
    @Property(2)
    @Default("null")
    val background: ColorResource?,
    @Property(3)
    @Default("null")
    val borderColor: ColorResource?,
    @Property(4)
    @Default("null")
    val cornerRadius: Int?,
    @Property(5)
    @Default("null")
    val textStyle: TextStyle?
)

@Widget(5)
data class Card(
    @Property(1) val background: ColorResource?,
    @Property(2)
    @Default("null")
    val cornerRadius: Int?,
    @Children(1) val child: () -> Unit,
)

//todo fix to normal list
@Widget(6)
data class Stack(
    @Children(1) val child1: @Composable () -> Unit,
    @Children(2) val child2: @Composable () -> Unit,
)