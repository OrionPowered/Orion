enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    plugins {
        id("com.github.johnrengelman.shadow") version "7.1.2"
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

rootProject.name = "orion-parent"

setupSubproject("anvil-native") {
    projectDir = file("anvil/native")
}

setupSubproject("anvil-java") {
    projectDir = file("anvil/java")
}

setupOrionSubproject("server")
setupOrionSubproject("api")

include("test-plugin")

fun setupOrionSubproject(name: String) {
    setupSubproject("orion-$name") {
        projectDir = file(name)
    }
}

inline fun setupSubproject(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}