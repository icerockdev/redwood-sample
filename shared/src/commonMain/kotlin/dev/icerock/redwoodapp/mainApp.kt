package dev.icerock.redwoodapp

import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.navigation.NavigationFactory
import dev.icerock.redwood.navigation.NavigationHost
import dev.icerock.redwood.navigation.NavigationRoot
import dev.icerock.redwood.navigation.navigation
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.navigation.scope.NavigationFactoryScope
import dev.icerock.redwoodapp.screens.demo.LoginScreen
import dev.icerock.redwoodapp.screens.demo.ProfileScreen
import dev.icerock.redwoodapp.screens.demo.TestCompleteScreen
import dev.icerock.redwoodapp.screens.demo.TestListScreen
import dev.icerock.redwoodapp.screens.demo.TestStepScreen
import dev.icerock.redwoodapp.screens.demo.ToogleScreen
import dev.icerock.redwoodapp.screens.demo.navigation.Screens
import org.example.library.MR

fun mainApp(
    navigationFactory: NavigationFactory
): NavigationRoot = navigation(
    navigationFactory = navigationFactory,
    startDestination = Screens.LOGIN
) {
    registerScreen(uri = Screens.LOGIN) { navigator, _ ->
        LoginScreen(navigator)
    }

    registerNavigation(uri = Screens.MAIN) { navigator, _ ->
        mainScreenNavigation(navigator)
    }
}

private fun NavigationFactoryScope.mainScreenNavigation(
    rootNavigator: Navigator
): NavigationHost = navigationTabs(startDestination = Screens.TEST_LIST) {
    registerNavigation(
        uri = Screens.TEST_LIST,
        title = MR.strings.tab_list.desc(),
        icon = MR.images.list,
        childNavigation = { _ ->
            secondTabNavigation()
        }
    )
    registerScreen(
        Screens.TOGGLE,
        title = MR.strings.tab_toggle.desc(),
        icon = MR.images.toggl,
    ) { _ ->
        ToogleScreen()
    }
    registerScreen(
        uri = Screens.PROFILE,
        title = MR.strings.tab_settings.desc(),
        icon = MR.images.profile,
        screen = {
            ProfileScreen(rootNavigator)
        }
    )
}

private fun NavigationFactoryScope.secondTabNavigation(
): NavigationHost = navigationFlat(startDestination = Screens.TEST_LIST) {
    registerScreen(Screens.TEST_LIST) { navigator, _ ->
        TestListScreen(navigator)
    }
    registerScreen(Screens.TEST_STEP) { navController, args ->
        TestStepScreen(
            navController,
            args["testId"]?.toInt() ?: 0
        )
    }
    registerScreen(Screens.TEST_FINAL) { navController, _ ->
        TestCompleteScreen(navController)
    }
}
