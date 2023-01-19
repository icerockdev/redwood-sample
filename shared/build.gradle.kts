plugins {
    kotlin("multiplatform")
    id("app.cash.redwood")
    id("dev.icerock.mobile.multiplatform-resources")
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
                api("dev.icerock.moko:resources:0.20.1")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
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
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library" // required
}
