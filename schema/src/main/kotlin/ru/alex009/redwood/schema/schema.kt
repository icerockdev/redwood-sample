package ru.alex009.redwood.schema

import app.cash.redwood.layout.RedwoodLayout
import app.cash.redwood.schema.Property
import app.cash.redwood.schema.Schema
import app.cash.redwood.schema.Schema.Dependency
import app.cash.redwood.schema.Widget

@Schema(
    members = [
        Text::class,
        Button::class,
    ],
    dependencies = [
        Dependency(1, RedwoodLayout::class),
    ],
)
interface RedwoodAppSchema

@Widget(2)
data class Text(
    @Property(1)
    val text: String,
)

@Widget(5)
data class Button(
    @Property(1)
    val text: String,
    @Property(2)
    val onClick: () -> Unit,
)
