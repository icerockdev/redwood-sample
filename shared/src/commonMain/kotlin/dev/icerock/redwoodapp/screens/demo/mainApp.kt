package dev.icerock.redwoodapp.screens.demo

import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.navigation.NavigationFactory
import dev.icerock.redwood.navigation.NavigationHost
import dev.icerock.redwood.navigation.NavigationRoot
import dev.icerock.redwood.navigation.navigation
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.navigation.scope.NavigationFactoryScope
import dev.icerock.redwood.schema.TextType
import dev.icerock.redwood.schema.compose.Text
import dev.icerock.redwoodapp.Box
import dev.icerock.redwoodapp.screens.demo.navigation.Screens
import org.example.library.MR

fun mainApp(
    navigationFactory: NavigationFactory
): NavigationRoot = navigation(
    navigationFactory = navigationFactory,
    startDestination = Screens.ONBOARDING
) {

    registerScreen(uri = Screens.ONBOARDING) { navigator, _ ->
        OnboardingScreen {
            navigator.navigate(Screens.LOGIN)
        }
    }
    registerScreen(uri = Screens.LOGIN) { navigator, _ ->
        LoginScreen(navigator)
    }

    registerNavigation(uri = Screens.MAIN) { navigator, _ ->
        mainScreenNavigation(navigator)
    }
}

private fun NavigationFactoryScope.mainScreenNavigation(
    rootNavigator: Navigator
): NavigationHost = navigationTabs(startDestination = Screens.TOGGLE) {
    registerScreen(
        Screens.TOGGLE,
        title = MR.strings.tab_toggle.desc(),
        icon = MR.images.home,
    ) { _ ->
        ToogleScreen()
    }
    registerScreen(
        uri = Screens.HR,
        title = MR.strings.tab_hr.desc(),
        icon = MR.images.mail,
        screen =  { _ ->
            HRScreen()
        }
    )
    registerNavigation(
        uri = Screens.TEST_LIST,
        title = MR.strings.tab_list.desc(),
        icon = MR.images.test,
        childNavigation = { _ ->
            secondTabNavigation()
        }
    )
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
