package ru.alex009.redwoodapp.android

import app.cash.redwood.layout.widget.RedwoodLayoutWidgetFactory
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactory
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactoryProvider

class SchemaWidgetFactories<W : Any>(
    override val RedwoodAppSchema: RedwoodAppSchemaWidgetFactory<W>,
    override val RedwoodLayout: RedwoodLayoutWidgetFactory<W>,
) : RedwoodAppSchemaWidgetFactoryProvider<W>