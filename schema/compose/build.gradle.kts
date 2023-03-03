plugins {
    kotlin("multiplatform")
    id("app.cash.redwood.generator.compose")
}

//archivesBaseName = "schema-compose"

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    jvm()

    sourceSets {
        commonMain {
            dependencies {
                api("app.cash.redwood:redwood-layout-compose:0.2.1")
                api(project(":schema:widget"))
            }
        }
    }
}

redwoodSchema {
    source.set(project(":schema"))
    type.set("dev.icerock.redwood.schema.RedwoodAppSchema")
}
