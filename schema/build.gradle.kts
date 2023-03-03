plugins {
    kotlin("jvm")
}

dependencies {
    api("app.cash.redwood:redwood-schema:0.2.1")
    api("app.cash.redwood:redwood-layout-schema:0.2.1")
    api("dev.icerock.moko:resources:0.20.1")
    api(project(":schema:entity"))
}
