import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    config.setFrom(rootProject.file("config/detekt.yml"))
    buildUponDefaultConfig = true
}

val detekt = tasks.register("detektWithoutTests") {
    dependsOn(tasks.withType<Detekt>().matching { it.name.contains("Test").not() })
}
tasks.matching { it.name == "check" }.configureEach {
    dependsOn(detekt)
}

dependencies {
    "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
}
