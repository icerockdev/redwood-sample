plugins {
    kotlin("jvm")
}

dependencies {
    api("app.cash.redwood:redwood-schema:0.1.0")
    api("app.cash.redwood:redwood-layout-schema:0.1.0")
    api(project(":schema:entity"))
}
