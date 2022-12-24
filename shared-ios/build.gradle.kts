plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("app.cash.redwood")
}

version = "0.1.0"

kotlin {
    iosArm64()
    iosX64()
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
    }
}
