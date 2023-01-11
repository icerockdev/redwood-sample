plugins {
    kotlin("multiplatform")
    id("app.cash.redwood.generator.widget")
}

//archivesBaseName = "schema-widget"

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    jvm()

    sourceSets {
        commonMain {
            dependencies {
                api("app.cash.redwood:redwood-layout-widget:0.1.0")
                api("app.cash.redwood:redwood-treehouse:0.1.0")
                api(project(":schema:entity"))
            }
        }
    }
}

redwoodSchema {
    source.set(project(":schema"))
    type.set("ru.alex009.redwood.schema.RedwoodAppSchema")
}