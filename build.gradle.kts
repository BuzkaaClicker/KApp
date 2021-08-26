import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jetbrains.compose") version "1.0.0-alpha3"
    id("io.gitlab.arturbosch.detekt") version "1.18.0"
}

group = "pl.buzkaaclicker"
version = "0.1"

repositories {
    google()
    mavenCentral()
    jcenter()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    detektPlugins("pl.setblack:kure-potlin:0.5.0")
    implementation(compose.desktop.currentOs)

    implementation(kotlin("stdlib"))
    implementation("io.vavr:vavr-kotlin:0.10.2")
    implementation("org.jetbrains.exposed", "exposed-core", "0.31.1")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.31.1")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.31.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("org.slf4j:slf4j-simple:1.7.32")
    implementation("dev.kord:kord-core:0.8.0-M5")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
}

compose.desktop {
    application {
        mainClass = "pl.buzkaaclicker.app.AppKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "BuzkaaClickerClient"
            packageVersion = "1.0.0"
        }
    }
}

detekt {
    config = files("$projectDir/detekt.yml")
}
