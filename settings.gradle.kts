enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "redwood-sample"

includeBuild("build-logic")

include(":androidApp")
include(":shared", ":shared-ios")
include(":schema", ":schema:widget", ":schema:compose", ":schema:entity")
