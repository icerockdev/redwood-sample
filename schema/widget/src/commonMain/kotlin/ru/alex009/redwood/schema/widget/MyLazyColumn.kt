package ru.alex009.redwood.schema.widget

import androidx.compose.runtime.Composable
import app.cash.redwood.widget.Widget

/**
 * {tag=8}
 */
public interface MySuperLazyColumn<W : Any> : Widget<W> {
  /**
   * {tag=1}
   */
  public fun childs(childs: List<Widget.Children<W>>): Unit

  public fun childs2(childs: List<@Composable ()->Unit>): Unit
}

interface MySuperLazyColumnFactory<W:Any>{
  public fun MySuperLazyColumn(): MySuperLazyColumn<W>
}
