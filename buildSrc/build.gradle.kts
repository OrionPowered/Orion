plugins {
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`
}

repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
    implementation("gradle.plugin.com.github.monosoul:yadegrap:1.0.0")
}