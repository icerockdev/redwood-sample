package dev.icerock.redwoodapp.android.widgets

import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.cash.redwood.LayoutModifier
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.redwood.schema.widget.Tabs
import dev.icerock.redwoodapp.android.theme.Colors
import dev.icerock.redwoodapp.android.theme.TextStyles

class ComposeTabs : Tabs<@Composable () -> Unit> {
    private var _texts: List<StringDesc> by mutableStateOf(listOf())
    private var _onClick: ((Int) -> Unit)? by mutableStateOf({})
    private var _selectedIndex: Int by mutableStateOf(0)
    override fun texts(texts: List<StringDesc>) {
        _texts = texts
    }

    override fun onChange(onChange: ((Int) -> Unit)?) {
        _onClick = onChange
    }

    override fun selectedTab(selectedTab: Int) {
        _selectedIndex = selectedTab
    }

    override var layoutModifiers: LayoutModifier = LayoutModifier
    override val value = @Composable {
        TabRow(selectedTabIndex = _selectedIndex,
        backgroundColor = Colors.white,
        contentColor = Colors.primary) {
           _texts.forEachIndexed{ index, tab ->
               Tab(
                   modifier = Modifier.height(48.dp),
                   selected = index == _selectedIndex,
                   onClick = {_onClick?.invoke(index)},
                   selectedContentColor = Colors.primary,
                   unselectedContentColor = Colors.black
               ){
                   Text(text = tab.toString(LocalContext.current),
                   style = TextStyles.primary)
               }
           }
        }
    }
}