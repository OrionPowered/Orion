import com.github.monosoul.yadegrap.DelombokTask

plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow")
    id("com.github.monosoul.yadegrap")
}

group = "pro.prysm"
version = "1.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

dependencies.compileOnly("org.projectlombok:lombok:1.18.22")
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

    val delombok = "delombok"(DelombokTask::class)

    "javadoc"(Javadoc::class) {
        dependsOn(delombok)
        setSource(delombok)
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
