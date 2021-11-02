plugins {
    kotlin("jvm") version "1.5.30"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "io.github.blugon09"
version = "1.0.0"




repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")

    implementation("net.kyori:adventure-api:4.9.2")
}

tasks {
    processResources {
        filesMatching("plugin.yml") {
            expand(project.properties)
        }
    }

    shadowJar {
        from(sourceSets["main"].output)
        archiveBaseName.set(project.name)
        archiveVersion.set("")
        archiveFileName.set("${project.name}.jar")

        doLast {
            copy {
                from(archiveFile)

                //Save Location
                val plugins = File("C:/Users/blugo/Desktop/Files/Minecraft/Servers/Default/plugins")
                into(plugins)
            }
        }
    }
}