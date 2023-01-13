plugins {
    id("org.jetbrains.kotlin.multiplatform")
    kotlin("native.cocoapods")
    id("app.cash.redwood")
    id("dev.icerock.mobile.multiplatform.ios-framework")
    id("dev.icerock.mobile.multiplatform.android-manifest")
}

version = "0.1.0"

framework {
    export(project(":schema:widget"))
    export(libs.mokoResources)
    export(libs.mokoGraphics)
}

kotlin {
    ios()
    iosSimulatorArm64()


    cocoapods {
        homepage = "https://github.com/alex009"
        summary = "redwood test"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":schema:widget"))
                implementation(project(":shared"))
                implementation("app.cash.redwood:redwood-layout-uiview:0.1.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            }
        }
        getByName("iosSimulatorArm64Main").dependsOn(getByName("iosMain"))
    }
}
