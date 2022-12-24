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

include(":androidApp")
include(":shared", ":shared-ios")
include(":schema", ":schema:widget", ":schema:compose")
