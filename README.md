# redwood-sample

Sample application for https://github.com/cashapp/redwood

![android-app](https://user-images.githubusercontent.com/26733767/215461637-3d4aac7b-ac64-4b22-b941-3bf5f9c009c9.gif)

![ios-app](https://user-images.githubusercontent.com/26733767/215436767-16d8fbb8-b9a3-4d11-8b24-a43ae5aa0692.gif)

Login screen example: 

```kotlin
@Composable
fun LoginScreen(navigator: Navigator) {
    Box {
        Column(
            horizontalAlignment = CrossAxisAlignment.Center,
            verticalAlignment = MainAxisAlignment.Center
        ) {
            var login: String by remember { mutableStateOf("") }
            var password: String by remember { mutableStateOf("") }
            Image(
                120,
                120,
                placeholder = MR.images.ava_preview,
                layoutModifier = LayoutModifier.padding(Padding(bottom = 100)),
                url = null
            )
            TextInput(login,
                MR.strings.auth_login.desc(),
                layoutModifier = LayoutModifier.padding(Padding(16)),
                onChange = {
                    login = it
                }
            )
            TextInput(
                password,
                MR.strings.auth_password.desc(),
                layoutModifier = LayoutModifier.padding(Padding(horizontal = 16)),
                onChange = {
                    password = it
                },
                inputType = InputType.Password
            )
            Button(
                MR.strings.auth_button.desc(), buttonType = ButtonType.Primary,
                enabled = login.isNotEmpty() && password.isNotEmpty(),
                onClick = {
                    navigator.navigate("tabs")
                }, layoutModifier = LayoutModifier.padding(Padding(start = 16, top = 100, end = 16))
            )
        }
    }
}
```

Flat navigation example: 

```kotlin
private fun secondTabNavigation() = navigation(startDestination = "start") {
    registerScreen(
        "start",
        isToolbarVisible = true,
    ) { navigator, _, screenSettings ->
        PostsList(screenSettings) { date, text ->
            navigator.navigate("/details/${date}?description=${text}")
        }
    }
    registerScreen(
        "/details/{date}?description={description}",
        isToolbarVisible = true
    ) { navController, args, screenSettings ->
        DetailsScreen(
            navController,
            args["date"].orEmpty(),
            args["description"].orEmpty(),
            screenSettings
        )
    }
}
```

Tab navigation example:

```kotlin
private fun mainScreenNavigation(rootNavigator: Navigator): NavigationHost =
    navigationTabs(startDestination = "line") {
        registerNavigation(
            uri = "line",
            title = MR.strings.tab_list.desc(),
            icon = MR.images.line,
            childNavigation = {
                secondTabNavigation()
            }
        )
        registerScreen(
            uri = "settings",
            title = MR.strings.tab_settings.desc(),
            icon = MR.images.settings,
            screen = {
                ProfileScreen(rootNavigator)
            }
        )
    }
```

## Run
### Android

Open in Android Studio and just run `androidApp` configuration.

### iOS

1. Run `pod install` in `iosApp` directory
2. Open `iosApp/iosApp.xcworkspace` in Xcode
3. Run app

## Notes

1. At now redwood successfully compiles on iOS only with Release configuration. Debug compilation fails.
2. Demo screen UI code in [shared](https://github.com/icerockdev/redwood-sample/blob/main/shared/src/commonMain/kotlin/ru/alex009/redwoodapp/HelloWorld.kt)
3. Android UI implemented with Jetpack Compose Material UI in [androidApp](androidApp/src/main/java/ru/alex009/redwoodapp/android/widgets)
4. iOS UI implemented with UIKit in [iosApp](iosApp/iosApp/Widgets)
