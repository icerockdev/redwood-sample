package ru.alex009.redwood.schema

import androidx.compose.runtime.Composable
import app.cash.redwood.layout.RedwoodLayout
import app.cash.redwood.layout.RowScope
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
        Card::class,
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
    @Default("14")
    val textSize: Int,
    @Property(3)
    @Default("0xFF000000")
    val color: Long,
)

@Widget(3)
data class Image(
    @Property(1) val url: String,
)

@Widget(4)
data class Card(
    @Children(1)
    val children: @Composable RowScope.() -> Unit,
)
