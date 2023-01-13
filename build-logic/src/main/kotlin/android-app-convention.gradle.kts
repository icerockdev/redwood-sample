@file:Suppress("UnstableApiUsage")

plugins {
    id("base-convention")
    id("com.android.application")
    id("android-base-convention")
    id("kotlin-android")
}

android {
    flavorDimensions.addAll(listOf("server"))

    signingConfigs {
        create("release") {
            keyAlias = System.getenv("RELEASE_KEY_ALIAS")
            keyPassword = System.getenv("RELEASE_KEY_PASSWORD")
            storeFile = file("signing/release.jks")
            storePassword = System.getenv("RELEASE_STORE_PASSWORD")
        }
        create("sharedDebug") {
            keyAlias = "debug"
            keyPassword = "debugicerock"
            storeFile = file("signing/debug.jks")
            storePassword = "debugicerock"
        }
    }

    defaultConfig {
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            val releaseConfig = signingConfigs.getByName("release")
            signingConfig = when {
                releaseConfig.keyAlias != null -> releaseConfig
                System.getenv("CI") == null -> {
                    logger.warn("used debug signing for release build!")
                    signingConfigs.getByName("sharedDebug")
                }
                else -> {
                    throw IllegalArgumentException("release signing not configured. Set RELEASE_KEY_ALIAS, RELEASE_KEY_PASSWORD, RELEASE_STORE_PASSWORD environment variables.")
                }
            }

            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("sharedDebug")
            isDebuggable = true
            applicationIdSuffix = ".debug"
            ext.set("enableCrashlytics", false)
        }
    }

    productFlavors {
        create("dev") {
            dimension = "server"
            applicationIdSuffix = ".dev"

            val endpoint = "https://api-loyalty-raffle.kube.uremont.dev/"
            buildConfigField("String", "BASE_URL", "\"$endpoint\"")
        }

        create("stage") {
            dimension = "server"
            applicationIdSuffix = ".stage"

            val endpoint = "https://newsapi.org/v2/"
            buildConfigField("String", "BASE_URL", "\"$endpoint\"")
        }

        create("prod") {
            dimension = "server"

            val endpoint = "https://newsapi.org/v2/"
            buildConfigField("String", "BASE_URL", "\"$endpoint\"")
        }
    }
    
    packagingOptions {
        resources.excludes.add("META-INF/*.kotlin_module")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}
