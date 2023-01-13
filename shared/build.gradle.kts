plugins {
    kotlin("multiplatform")
    id("app.cash.redwood")
    id("dev.icerock.mobile.multiplatform-resources")
}
dependencies {
    commonMainApi("dev.icerock.moko:resources:0.20.1")
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library" // required
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