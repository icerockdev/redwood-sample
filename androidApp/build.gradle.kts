plugins {
    id("com.android.application")
    kotlin("android")
    id("app.cash.redwood")
}

android {
    namespace = "dev.icerock.redwoodapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "dev.icerock.redwoodapp.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0-alpha01"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:1.3.2")
    implementation("androidx.compose.ui:ui-tooling:1.3.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.2")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("com.google.accompanist:accompanist-pager:0.28.0")
    implementation("androidx.activity:activity-compose:1.6.1")

    implementation(project(":shared"))
    implementation("app.cash.redwood:redwood-layout-composeui:0.2.1")
    implementation("app.cash.redwood:redwood-widget-compose-jvm:0.2.1")

    implementation("com.github.skydoves:landscapist-coil:1.4.8")
}