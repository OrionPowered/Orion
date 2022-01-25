plugins {
    java
}

group = "pro.prysm"
version = "1.0-SNAPSHOT"
description = "Orion test plugin"

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

tasks {
    // Variable replacements
    processResources {
        filesMatching(listOf("plugin.json")) {
            expand("version" to project.version, "description" to project.description)
        }
    }
}

dependencies {
    implementation(projects.orionApi)
}
