plugins {
    kotlin("multiplatform")
    id("app.cash.redwood")
}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":schema:compose"))
            }
        }
    }
}
