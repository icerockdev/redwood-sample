plugins {
    kotlin("multiplatform")
    id("app.cash.redwood")
    id("com.android.library")
}

android {
    namespace = "dev.icerock.redwood.navigation"
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
                api("app.cash.redwood:redwood-widget:0.2.1")
                api("app.cash.redwood:redwood-compose:0.2.1")
                api("dev.icerock.moko:resources:0.20.1")
                api("dev.icerock.moko:mvvm-core:0.15.0")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
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
                api("androidx.compose.foundation:foundation:1.3.1")
                api("androidx.compose.foundation:foundation-layout:1.3.1")
                api("androidx.compose.material:material:1.3.1")

                api("androidx.navigation:navigation-compose:2.5.3")
                api("androidx.navigation:navigation-runtime-ktx:2.5.3")
            }
        }
    }
}
