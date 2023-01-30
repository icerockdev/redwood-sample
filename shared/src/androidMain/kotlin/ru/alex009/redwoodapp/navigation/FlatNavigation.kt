package ru.alex009.redwoodapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.cash.redwood.widget.Widget
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import ru.alex009.redwoodapp.shared.R

data class FlatNavigation(
    val startDestination: String,
    val routes: MutableMap<String, FlatRouteData>,
    val navBarVisibility: MutableMap<String, Boolean>,
    val screenSettings: ScreenSettingsImpl
) : NavigationHost {

    @Composable
    override fun Render(provider: Widget.Provider<() -> Unit>) {
        val navController: NavHostController = rememberNavController()

        val nav: Navigator = remember(navController) {
            object : Navigator {
                override fun navigate(uri: String) {
                    navController.navigate(uri)
                }

                override fun popBackStack() {
                    navController.popBackStack()
                }
            }
        }

        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            routes.forEach { item ->
                val argsNameList: List<String> = item.key.getArgs()// + item.key.getParams()
                composable(
                    route = item.key,
                    arguments = if (argsNameList.isEmpty()) {
                        listOf()
                    } else {
                        val result: MutableList<NamedNavArgument> = mutableListOf()
                        argsNameList.forEach {
                            result.add(
                                navArgument(it) { type = NavType.StringType }
                            )
                        }
                        result
                    }
                ) { entry ->
                    Scaffold(
                        topBar = {
                            if (navBarVisibility[navController.currentDestination?.route] == true) {
                                TopAppBar(
                                    backgroundColor = Color.White,
                                    contentColor = Color.Black,
                                    elevation = 2.dp,
                                ) {
                                    Box(modifier = Modifier.fillMaxWidth()) {
                                        // todo fix
                                        if (navController.backQueue.size != 2) {
                                            IconButton(
                                                modifier = Modifier.align(Alignment.CenterStart),
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
                                        Text(
                                            modifier = Modifier.align(Alignment.Center),
                                            text = screenSettings.text.toString(LocalContext.current)
                                        )
                                    }
                                }
                            }
                        }
                    ) { innerPadding ->
                        Box(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
                            val argsMap = mutableMapOf<String, String>()

                            argsNameList.forEach {
                                val accountType =
                                    if (argsNameList.isEmpty()) null else entry.arguments?.getString(
                                        it
                                    )
                                if (accountType != null) {
                                    argsMap[it] = accountType
                                }
                            }

                            val tmp = entry.arguments?.toString()?.substringAfter("dat=")
                                ?.substringBefore('}')
                            val params = tmp?.substringAfter('?')
                            params?.split('&')?.forEach {
                                argsMap[it.substringBefore('=')] = it.substringAfter('=')
                            }

                            item.value(provider, nav, argsMap)
                        }
                    }
                }
            }
        }
    }
}

fun String.getArgs(): List<String> {
    val result = mutableListOf<String>()

    val tmp = this.substringBefore('?').split('/')
    if (tmp.isEmpty() || tmp.size == 1) return listOf()
    tmp.forEach {
        if (it.contains('}')) {
            result.add(
                it.removePrefix("{")
                    .removeSuffix("}")
            )
        }
    }

    return result
}

fun String.getParams(): List<String> {
    val result = mutableListOf<String>()

    val tmp = this.substringAfter('?').split('&')
    if (tmp.isEmpty() || tmp.size == 1) return listOf()
    tmp.forEach {
        if (it.contains('}')) {
            result.add(
                it.removePrefix("{")
                    .removeSuffix("}")
            )
        }
    }

    return result
}


class ScreenSettingsImpl() : ScreenSettings {

    var text: StringDesc by mutableStateOf("".desc())

    lateinit var viewModelStoreOwner: ViewModelStoreOwner
    override fun setTitle(title: StringDesc) {
        this.text = title
    }

}