plugins {
    id("pro.prysm.java-conventions")
}

dependencies {
    implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")
    compileOnly("com.google.code.gson:gson:2.8.9")
    compileOnly("net.kyori:adventure-api:4.9.3")
    compileOnly("net.kyori:adventure-text-serializer-gson:4.9.3")
    compileOnly("io.netty:netty-all:4.1.72.Final")
    system("com.alexsobiek:anvil:1.0-ALPHA")
}

group = "pro.prysm.orion"
description = "api"
