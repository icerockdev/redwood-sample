plugins {
    id("org.jetbrains.kotlin.multiplatform")
    kotlin("native.cocoapods")
    id("app.cash.redwood")
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
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":schema:widget"))
                api(project(":shared"))
                implementation("app.cash.redwood:redwood-layout-uiview:0.1.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            }
        }
        getByName("iosSimulatorArm64Main").dependsOn(getByName("iosMain"))
    }
}
