plugins {
    id("pro.prysm.java-conventions")
    id("fr.stardustenterprises.rust.importer") version "2.1.0"
    application
}

dependencies {
    rustImport(project(":orion-lib-anvil"))
    api(projects.orionCommon)
    api(projects.orionApi)

    implementation("com.velocitypowered:velocity-native:3.0.1-SNAPSHOT")
    api("net.kyori:adventure-nbt:4.9.3")
}

application {
    mainClass.set("pro.prysm.orion.server.Orion")
}

tasks {
    fixImport {
        enabled = false
    }
}

tasks.named<JavaExec>("run") {
    val file = file(rootDir.path + "/run/server")
    file.mkdirs()
    workingDir = file
}

tasks.runShadow {
    val file = file(rootDir.path + "/run/server")
    file.mkdirs()
    workingDir = file
}

tasks.withType<Jar> {
    dependsOn("unpackExports", "deleteExports")
    manifest {
        attributes["Main-Class"] = "pro.prysm.orion.server.Orion"
    }
}

tasks.withType<ProcessResources> {
    finalizedBy("unpackExports", "deleteExports")
}

tasks.register<Copy>("unpackExports") {
    from(zipTree(layout.buildDirectory.file("resources/main/export.zip")))
    into(layout.buildDirectory.dir("resources/main"))
    finalizedBy("deleteExports")
}

tasks.register<Delete>("deleteExports") {
    delete(layout.buildDirectory.file("resources/main/export.zip"))
}
