plugins {
    kotlin("multiplatform")
    id("app.cash.redwood")
    id("dev.icerock.mobile.multiplatform-resources")
    id("com.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "ru.alex009.redwoodapp.shared"
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
                api("dev.icerock.moko:resources:0.20.1")
                api("dev.icerock.moko:parcelize:0.8.0")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation("app.cash.redwood:redwood-layout-uiview:0.1.0")
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
                implementation("androidx.activity:activity-compose:1.6.1")
                implementation("androidx.compose.ui:ui:1.3.3")
                implementation("androidx.compose.ui:ui-tooling:1.3.3")
                implementation("androidx.compose.foundation:foundation:1.3.1")
                implementation("androidx.compose.foundation:foundation-layout:1.3.1")
                implementation("androidx.compose.material:material:1.3.1")
                implementation("com.google.android.material:compose-theme-adapter:1.2.1")
                implementation("androidx.navigation:navigation-compose:2.5.3")
                implementation("androidx.navigation:navigation-runtime-ktx:2.5.3")
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library" // required
}
