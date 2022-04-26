plugins {
    id("fr.stardustenterprises.rust.wrapper") version "2.1.0"
}

rust {
    command = "cargo"

    outputs = mapOf("" to System.mapLibraryName("orionfastanvil"))

    outputDirectory = "META-INF/native"

    profile = "release"
}