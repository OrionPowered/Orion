plugins {
    id("pro.prysm.java-conventions")
}

dependencies {
    implementation("io.netty:netty-all:4.1.72.Final")
    implementation(project(":api"))
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("com.velocitypowered:velocity-native:3.0.1-SNAPSHOT")
    implementation("net.kyori:adventure-nbt:4.9.3")
    implementation("net.kyori:adventure-api:4.9.3")
    implementation("net.kyori:adventure-text-serializer-gson:4.9.3")
    implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")
    system("com.alexsobiek:anvil:1.0-ALPHA")
}

group = "pro.prysm.orion"
description = "server"
