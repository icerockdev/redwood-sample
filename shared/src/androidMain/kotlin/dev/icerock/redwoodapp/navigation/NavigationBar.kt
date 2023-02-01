@file:OptIn(ExperimentalUnitApi::class)

package dev.icerock.redwoodapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwoodapp.shared.R
import kotlinx.coroutines.flow.Flow

actual sealed class NavigationBar {

    @Composable
    abstract fun Render(navController: NavController)

    actual class SimpleNavigationBar actual constructor(block: SimpleNavigationBarDsl.() -> Unit) :
        NavigationBar() {

        var title by mutableStateOf<StringDesc>("".desc())
        var actions by mutableStateOf<List<NavBarAction>>(listOf())

        val dsl = object : SimpleNavigationBarDsl {
            override fun setTitle(text: StringDesc) {
                title = text
            }

            override fun addAction(
                icon: ImageResource,
                badge: Flow<StringDesc>?,
                onClick: () -> Unit
            ) {
                actions = actions.plus(
                    NavBarAction(
                        icon,
                        null,
                        badge,
                        onClick
                    )
                )
            }

            override fun addAction(
                icon: Flow<ImageResource>,
                badge: Flow<StringDesc>?,
                onClick: () -> Unit
            ) {
                actions = actions.plus(
                    NavBarAction(
                        null,
                        icon,
                        badge,
                        onClick
                    )
                )
            }

        }

        init {
            dsl.block()
        }

        @Composable
        override fun Render(navController: NavController) {
            TopAppBar(
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 2.dp,
                title = {
                    Text(
                        text = title.toString(LocalContext.current)
                    )
                },
                navigationIcon = {
                    if (navController.backQueue.size != 2) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_left),
                                contentDescription = null
                            )
                        }
                    }
                },
                actions = {
                    actions.forEach {
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .clickable {
                                    it.onClick()
                                }
                        ) {
                            val iconState = it.iconFlow?.collectAsState(null)
                            val icon = iconState?.value ?: it.icon
                            if(icon!=null) {
                                Image(
                                    modifier = Modifier.align(Alignment.Center)
                                        .width(24.dp)
                                        .height(24.dp),
                                    painter = painterResource(
                                        id = icon.drawableResId
                                    ),
                                    contentDescription = ""
                                )
                            }
                            if (it.badge != null) {
                                val text by it.badge.collectAsState(initial = "".desc())
                                Text(
                                    text = text.toString(LocalContext.current),
                                    fontSize = TextUnit(10f, TextUnitType.Sp),
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .clip(CircleShape)
                                        .background(Color.Red)
                                        .padding(1.dp)
                                        .align(Alignment.TopEnd)
                                        .requiredWidth(12.dp),
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            )
        }
    }

    actual class SearchNavigationBar actual constructor(block: SearchNavigationBarDsl.() -> Unit) :
        NavigationBar() {
        @Composable
        override fun Render(navController: NavController) {

        }
    }
}

data class NavBarAction(
    val icon: ImageResource?,
    val iconFlow: Flow<ImageResource>?,
    val badge: Flow<StringDesc>?,
    val onClick: () -> Unit
)