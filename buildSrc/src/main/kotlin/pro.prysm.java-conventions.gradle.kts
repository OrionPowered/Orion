plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow")
}

group = "pro.prysm"
version = "1.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

dependencies.implementation("org.projectlombok:lombok:1.18.22")
dependencies.annotationProcessor("org.projectlombok:lombok:1.18.22")

tasks {
    jar {
        archiveClassifier.set("unshaded")
    }

    shadowJar {
        minimize()
        archiveClassifier.set("shaded")
    }

    build {
        dependsOn(shadowJar)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks["shadowJar"])
        }
    }
}
