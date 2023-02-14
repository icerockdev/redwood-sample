plugins {
    kotlin("multiplatform")
    id("app.cash.redwood")
    id("dev.icerock.mobile.multiplatform-resources")
    id("com.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "dev.icerock.redwoodapp.shared"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    buildFeatures {
        compose = true
    }
}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":schema:compose"))
                api(project(":navigation"))
                api("dev.icerock.moko:resources:0.20.1")
                api("dev.icerock.moko:mvvm-core:0.15.0")
                api("dev.icerock.moko:fields-core:0.11.0")
                api("dev.icerock.moko:fields-flow:0.11.0")
                api("app.cash.redwood:redwood-treehouse:0.2.0")
                api("app.cash.redwood:redwood-treehouse-lazylayout-compose:0.2.0")
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                api("com.russhwolf:multiplatform-settings:0.9")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation("app.cash.redwood:redwood-layout-uiview:0.2.0")
            }
        }

        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosX64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.compose.ui:ui:1.3.3")
                api("androidx.compose.ui:ui-tooling:1.3.3")
                api("androidx.compose.foundation:foundation:1.3.1")
                api("androidx.compose.foundation:foundation-layout:1.3.1")
                api("androidx.compose.material:material:1.3.1")
                api("com.google.android.material:compose-theme-adapter:1.2.1")
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library" // required
}
