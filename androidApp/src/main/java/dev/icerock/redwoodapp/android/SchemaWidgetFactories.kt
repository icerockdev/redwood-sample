package dev.icerock.redwoodapp.android

import app.cash.redwood.layout.widget.RedwoodLayoutWidgetFactory
import dev.icerock.redwood.schema.widget.RedwoodAppSchemaWidgetFactory
import dev.icerock.redwood.schema.widget.RedwoodAppSchemaWidgetFactoryProvider

class SchemaWidgetFactories<W : Any>(
    override val RedwoodAppSchema: RedwoodAppSchemaWidgetFactory<W>,
    override val RedwoodLayout: RedwoodLayoutWidgetFactory<W>,
) : RedwoodAppSchemaWidgetFactoryProvider<W>