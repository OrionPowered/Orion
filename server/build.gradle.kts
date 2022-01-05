plugins {
    id("pro.prysm.java-conventions")
    application
}

dependencies {
    api("com.velocitypowered:velocity-native:3.0.1-SNAPSHOT")
    api("net.kyori:adventure-nbt:4.9.3")

    implementation(projects.orionApi)
    implementation(projects.anvilJava)
}

application {
    mainClass.set("net.prysmatic.orion.Orion")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "pro.prysm.orion.server.Orion"
    }
}
