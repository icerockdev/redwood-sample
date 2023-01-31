plugins {
    id("org.jetbrains.kotlin.multiplatform")
    kotlin("native.cocoapods")
    id("app.cash.redwood")
    id("dev.icerock.mobile.multiplatform-resources")
}

version = "0.1.0"

kotlin {
    ios()
    iosSimulatorArm64()

    cocoapods {
        homepage = "https://github.com/icerockdev"
        summary = "redwood test"

        framework {
            isStatic = false

            export(project(":schema:widget"))
            export("dev.icerock.moko:resources:0.20.1")
            export("dev.icerock.moko:mvvm-core:0.15.0")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":schema:widget"))
                api(project(":shared"))
                api("dev.icerock.moko:resources:0.20.1")

                api("dev.icerock.moko:mvvm-core:0.15.0")


                implementation("app.cash.redwood:redwood-layout-uiview:0.1.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            }
        }
        getByName("iosSimulatorArm64Main").dependsOn(getByName("iosMain"))
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.shared_ios" // required
}

