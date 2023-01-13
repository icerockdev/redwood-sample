# redwood-sample

Sample application for https://github.com/cashapp/redwood



## Run
### Android

Open in Android Studio and just run `androidApp` configuration.

### iOS

1. Run `pod install` in `iosApp` directory
2. Open `iosApp/iosApp.xcworkspace` in Xcode
3. Run app

## Notes

1. At now redwood successfully compiles on iOS only with Release configuration. Debug compilation fails.
2. Android UI implemented with Jetpack Compose Material UI in [androidApp](androidApp/src/main/java/ru/alex009/redwoodapp/android/widgets)
3. iOS UI implemented with UIKit in [iosApp](iosApp/iosApp/Widgets)
