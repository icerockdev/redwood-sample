package ru.alex009.redwood.schema

import app.cash.redwood.layout.RedwoodLayout
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
)

@Widget(3)
data class Image(
    @Property(1) val url: String,
)
