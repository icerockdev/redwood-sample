plugins {
    val androidGradlePluginVersion = "7.3.1"
    val kotlinGradlePluginVersion = "1.7.20"

    id("com.android.application").version(androidGradlePluginVersion).apply(false)
    id("com.android.library").version(androidGradlePluginVersion).apply(false)
    kotlin("android").version(kotlinGradlePluginVersion).apply(false)
    kotlin("multiplatform").version(kotlinGradlePluginVersion).apply(false)
    id("app.cash.redwood").version("0.1.0").apply(false)
    id("org.jetbrains.compose").version("1.2.2").apply(false)
}

buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    dependencies {
        classpath ("dev.icerock.moko:resources-generator:0.20.1")
    }
}


allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
