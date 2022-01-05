plugins {
    id("pro.prysm.java-conventions")
}

dependencies {
    api("com.velocitypowered:velocity-native:3.0.1-SNAPSHOT")
    api("net.kyori:adventure-nbt:4.9.3")

    implementation(projects.orionApi)
    implementation(projects.anvilJava)
}
