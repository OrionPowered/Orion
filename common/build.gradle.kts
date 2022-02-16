plugins {
    id("pro.prysm.java-conventions")
}

dependencies {
    api(projects.orionApi)

    implementation("io.netty:netty-all:4.1.72.Final")
    implementation("com.velocitypowered:velocity-native:3.0.1-SNAPSHOT")
    implementation("ch.qos.logback:logback-classic:1.3.0-alpha12")
    implementation("org.jetbrains:annotations:20.1.0")
}