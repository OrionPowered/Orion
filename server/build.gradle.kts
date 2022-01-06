plugins {
    id("pro.prysm.java-conventions")
    application
}

dependencies {
    implementation(project(":orion-api", configuration = "shadow"))
    implementation(project(":anvil-java"))

    implementation("com.velocitypowered:velocity-native:3.0.1-SNAPSHOT")
    implementation("net.kyori:adventure-nbt:4.9.3")
}

application {
    mainClass.set("pro.prysm.orion.server.Orion")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "pro.prysm.orion.server.Orion"
    }
}
