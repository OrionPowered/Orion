plugins {
    id("pro.prysm.java-conventions")
    application
}

dependencies {
    api(projects.orionApi)

    implementation("com.velocitypowered:velocity-native:3.0.1-SNAPSHOT")
    implementation("net.kyori:adventure-nbt:4.9.3")
}

application {
    mainClass.set("pro.prysm.orion.server.Orion")
}

tasks.named<JavaExec>("run") {
    val file = file(rootDir.path + "/run")
    file.mkdirs()
    workingDir = file
}

tasks.runShadow {
    val file = file(rootDir.path + "/run")
    file.mkdirs()
    workingDir = file
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "pro.prysm.orion.server.Orion"
    }
}
