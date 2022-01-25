plugins {
    java
}

group = "pro.prysm"
version = "1.0-SNAPSHOT"
description = "Orion test module"

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

tasks {
    // Variable replacements
    processResources {
        filesMatching(listOf("module.json")) {
            expand("version" to project.version, "description" to project.description)
        }
    }
}

dependencies {
    implementation(projects.orionServer)
    implementation(projects.orionApi)
}
