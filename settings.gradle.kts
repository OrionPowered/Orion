enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    plugins {
        id("com.github.johnrengelman.shadow") version "7.1.2"
        id("com.github.monosoul.yadegrap") version "1.0.0"
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/") {
            name = "PaperMC"
        }
        maven("https://oss.sonatype.org/content/groups/public/") {
            name = "Sonatype OSS Snapshots"
        }
    }
}

rootProject.name = "Orion"

setupOrionSubproject("common")
setupOrionSubproject("server")
setupOrionSubproject("proxy")
setupOrionSubproject("api")

include("test-plugin")
include("test-module")

fun setupOrionSubproject(name: String) {
    setupSubproject("orion-$name") {
        projectDir = file(name)
    }
}

inline fun setupSubproject(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}