plugins {
    id("fr.stardustenterprises.rust.wrapper") version "2.1.0"
}

rust {
    command = "cargo"

    outputs = mapOf("" to System.mapLibraryName("orion_anvil"))

    outputDirectory = "META-INF/native"

    profile = "release"
}