plugins {
    java
    id("com.google.protobuf") version "0.9.4"
    id("io.github.goooler.shadow") version "8.1.3"
}

group = "dev.twelveoclock"
version = "1.0.0"

repositories {

    mavenCentral()

    maven("https://hub.spigotmc.org/nexus/content/repositories/public/") {
        name = "SpigotMC"
    }
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "PaperMC"
    }
}

dependencies {

    compileOnly("org.spigotmc:spigot-api:1.20.4-R0.1-SNAPSHOT")

    implementation("org.jetbrains:annotations:24.1.0")
    implementation("com.google.protobuf:protobuf-java:3.25.3")

    // Jackson
    implementation("com.fasterxml.jackson.core:jackson-core:2.16.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-toml:2.16.1")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("com.github.seeseemelk:MockBukkit-v1.17:1.13.0")
}


tasks {

    test {
        useJUnitPlatform()
    }

    // TODO: Change the second parameter to your plugin's package + the suffix.
    //       For example, if your main package is "me.example.catplugin",
    //       change the second parameter for the first relocate to:
    //       "me.example.catplugin.libs.com.fasterxml".
    //       Then, follow this pattern to the other relocate calls.
    shadowJar {
        relocate("com.fasterxml", "dev.twelveoclock.plugintemplate.libs.com.fasterxml")
        relocate("org.jetbrains", "dev.twelveoclock.plugintemplate.libs.org.jetbrains")
        relocate("org.intellij", "dev.twelveoclock.plugintemplate.libs.org.intellij")
    }
}

