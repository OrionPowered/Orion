plugins {
    id("pro.prysm.java-conventions")
}

dependencies {
    api("com.google.code.gson:gson:2.8.9")
    api("net.kyori:adventure-api:4.9.3")
    api("net.kyori:adventure-text-serializer-gson:4.9.3")
    api("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")
    api("io.netty:netty-all:4.1.72.Final")

    api("ch.qos.logback:logback-classic:1.3.0-alpha12")
    api("org.fusesource.jansi:jansi:2.4.0")
}
