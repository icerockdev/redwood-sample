plugins {
    kotlin("multiplatform")
    id("app.cash.redwood.generator.widget")
}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    jvm()

    sourceSets {
        commonMain {
            dependencies {
                api("app.cash.redwood:redwood-layout-widget:0.2.0")
                api(project(":schema:entity"))
            }
        }
    }
}

redwoodSchema {
    source.set(project(":schema"))
    type.set("dev.icerock.redwood.schema.RedwoodAppSchema")
}
