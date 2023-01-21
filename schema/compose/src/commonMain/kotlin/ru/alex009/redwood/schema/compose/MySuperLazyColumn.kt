package ru.alex009.redwood.schema.compose

import androidx.compose.runtime.Composable
import app.cash.redwood.LayoutModifier
import app.cash.redwood.RedwoodCodegenApi
import app.cash.redwood.compose.RedwoodComposeNode
import app.cash.redwood.widget.Widget
import ru.alex009.redwood.schema.widget.Card
import ru.alex009.redwood.schema.widget.MySuperLazyColumn
import ru.alex009.redwood.schema.widget.MySuperLazyColumnFactory
import ru.alex009.redwood.schema.widget.RedwoodAppSchemaWidgetFactoryProvider

@Composable
@OptIn(RedwoodCodegenApi::class)
public fun MySuperLazyColumn(childs: List<@Composable ()->Unit>, layoutModifier: LayoutModifier =
    LayoutModifier): Unit {
  RedwoodComposeNode<RedwoodAppSchemaWidgetFactoryProvider<*>, MySuperLazyColumn<*>>(
      factory = { (it.RedwoodAppSchema as MySuperLazyColumnFactory<*>).MySuperLazyColumn() },
      update = {
          set(layoutModifier) { layoutModifiers = it }
          set(childs, MySuperLazyColumn<*>::childs2 )
      },
      content = {
        into()
      },
      )
}
