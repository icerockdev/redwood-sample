package dev.icerock.redwoodapp.screens.demo_amway

import dev.icerock.moko.resources.desc.desc
import dev.icerock.redwood.navigation.NavigationFactory
import dev.icerock.redwood.navigation.NavigationHost
import dev.icerock.redwood.navigation.NavigationRoot
import dev.icerock.redwood.navigation.navigation
import dev.icerock.redwood.navigation.navigator.Navigator
import dev.icerock.redwood.navigation.scope.NavigationFactoryScope
import dev.icerock.redwoodapp.screens.demo.navigation.Screens
import org.example.library.MR

fun amwayApp(
    navigationFactory: NavigationFactory
): NavigationRoot = navigation(
    navigationFactory = navigationFactory,
    startDestination = Screens.PHONE_NUMBER_LOGIN
) {

    registerScreen(uri = Screens.PHONE_NUMBER_LOGIN) { navigator, _ ->
        LoginScreen {
            navigator.navigate(Screens.SMS_CODE)
        }
    }


    registerScreen(uri = Screens.SMS_CODE) { navigator, _ ->
        RegistrationScreen {
            navigator.navigate("product")
        }
    }

    registerScreen(uri = "product") { navigator, _ ->
        SelectProduct {
            navigator.navigate("target")
        }
    }


    registerScreen(uri = "target") { navigator, _ ->
        TargetScreen {
            navigator.navigate(Screens.MAIN_NAVIGATION)
        }
    }


    registerNavigation(uri = Screens.MAIN_NAVIGATION) { navigator, _ ->
        mainScreenNavigation(navigator)
    }
}

private fun NavigationFactoryScope.mainScreenNavigation(
    rootNavigator: Navigator
): NavigationHost = navigationTabs(startDestination = Screens.MAIN) {
   /* registerScreen(
        Screens.MAIN,
        title = MR.strings.tab_main.desc(),
        icon = MR.images.tab_main_color,
    ) { _ ->
        MainScreen()
    }
    registerScreen(
        uri = Screens.CATALOG,
        title = MR.strings.tab_catalog.desc(),
        icon = MR.images.tab_catalog,
        screen =  { _ ->
            CatalogScreen()
        }
    )
    registerScreen(
        uri = Screens.SERVICE,
        title = MR.strings.tab_service.desc(),
        icon = MR.images.tab_service,
        screen =  { _ ->
            ServiceScreen()
        }
    )
    /*registerNavigation(
        uri = Screens.TEST_LIST,
        title = MR.strings.tab_list.desc(),
        icon = MR.images.test,
        childNavigation = { _ ->
            secondTabNavigation()
        }
    )*/
    registerScreen(
        uri = Screens.PROFILE,
        title = MR.strings.tab_profile.desc(),
        icon = MR.images.tab_profile,
        screen = {
            ProfileScreen(rootNavigator)
        }

    )*/
}
