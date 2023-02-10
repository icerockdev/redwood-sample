package dev.icerock.redwoodapp.android.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwoodapp.ToolabrArgs
import dev.icerock.redwoodapp.android.theme.Colors
import dev.icerock.redwoodapp.navigation.FlatNavigationFactory
import dev.icerock.redwoodapp.shared.R

@OptIn(ExperimentalUnitApi::class)
class DefaultNavigationTapbar : FlatNavigationFactory<ToolabrArgs> {

    @Composable
    override fun RenderToolbar(navController: NavController, data: ToolabrArgs) {
        when {
            data is ToolabrArgs.Simple ->
                TopAppBar(
                    backgroundColor = Color.White,
                    contentColor = Color.Black,
                    elevation = 2.dp,
                    title = {
                        Text(
                            text = data.title.toString(LocalContext.current)
                        )
                    },
                    navigationIcon = if (navController.backQueue.size != 2) {
                        {
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
                    } else {
                        null
                    },
                    actions = {
                        data.actoins.forEach {
                            Box {
                                IconButton(
                                    onClick = it.onCLick
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = it.icon.drawableResId
                                        ),
                                        contentDescription = ""
                                    )
                                }
                                val badge = it.badge
                                if (badge != null) {
                                    Text(
                                        text = badge.toString(LocalContext.current),
                                        fontSize = TextUnit(10f, TextUnitType.Sp),
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .clip(CircleShape)
                                            .background(Color.Red)
                                            .padding(1.dp)
                                            .align(Alignment.TopEnd)
                                            .defaultMinSize(12.dp),
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                )
            data is ToolabrArgs.NoToolbar -> {}
            data is ToolabrArgs.Search -> {
                Column(
                    modifier = Modifier
                        .background(Colors.gray50)
                ) {
                    TopAppBar(
                        backgroundColor = Color.Transparent,
                        contentColor = Color.Black,
                        elevation = 0.dp,
                        title = {
                            Box(modifier = Modifier.padding(vertical = 6.dp)) {
                                SearchInput(
                                    inputField = data.search.data,
                                    placeholder = data.placeholder ?: "".desc()
                                )
                            }
                        },
                        navigationIcon = if (navController.backQueue.size != 2) {
                            {
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
                        } else {
                            null
                        },
                        actions = {
                            Box {
                                IconButton(
                                    onClick = data.actoin.onCLick
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = dev.icerock.redwoodapp.android.R.drawable.baseline_shopping_cart_24//data.actoin.icon.drawableResId
                                        ),
                                        colorFilter = ColorFilter.tint(Colors.black),
                                        contentDescription = ""
                                    )
                                }
                                val badge = data.actoin.badge
                                if (badge != null) {
                                    Text(
                                        text = badge.toString(LocalContext.current),
                                        fontSize = TextUnit(10f, TextUnitType.Sp),
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .clip(CircleShape)
                                            .background(Color.Red)
                                            .padding(1.dp)
                                            .align(Alignment.TopEnd)
                                            .defaultMinSize(12.dp),
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    )
                    Box (modifier = Modifier.fillMaxWidth()){
                        data.leftButton?.let {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 8.dp, bottom = 8.dp)
                                    .clickable { it.onCLick() }
                                    .padding(end = 8.dp)
                                    .clip(CircleShape),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier.size(40.dp),
                                    contentScale = ContentScale.Inside,
                                    painter = painterResource(id = it.icon.drawableResId),
                                    colorFilter = ColorFilter.tint(Colors.black),
                                    contentDescription = ""
                                )
                                Text(text = it.title.toString(LocalContext.current))
                            }
                        }
                        data.rightButton?.let {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 8.dp, bottom = 8.dp)
                                    .clickable { it.onCLick() }
                                    .padding(start = 8.dp)
                                    .clip(CircleShape),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier.size(40.dp),
                                    contentScale = ContentScale.Inside,
                                    painter = painterResource(id = it.icon.drawableResId),
                                    colorFilter = ColorFilter.tint(Colors.black),
                                    contentDescription = ""
                                )
                                Text(text = it.title.toString(LocalContext.current))
                            }
                        }
                    }
                }
            }
            else -> {}
        }
    }
}

