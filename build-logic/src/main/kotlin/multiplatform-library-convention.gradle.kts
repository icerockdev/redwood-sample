plugins {
    id("base-convention")
    id("com.android.library")
    id("android-base-convention")
    id("org.jetbrains.kotlin.multiplatform")
    id("dev.icerock.mobile.multiplatform.android-manifest")
}

kotlin {
    android()
    ios()
    iosSimulatorArm64()

    sourceSets {
        getByName("iosSimulatorArm64Main").dependsOn(getByName("iosMain"))
        getByName("iosSimulatorArm64Test").dependsOn(getByName("iosTest"))
    }
}
