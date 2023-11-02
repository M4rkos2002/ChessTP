import java.net.URI

plugins {
    application
    `java-library`
    `maven-publish`
    id("java")
    kotlin("jvm") version "1.7.10"
    id("org.openjfx.javafxplugin").version("0.0.13")

}

group = "edu.austral.dissis.chess"
version = "1.0.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("edu.austral.dissis.chess.gui.ChessGameApplication")
}

javafx {
    version = "18"
    modules = listOf("javafx.graphics")
}

tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
            "Implementation-Version" to project.version))
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = URI.create("https://maven.pkg.github.com/austral-ingsis/chess-ui")
            credentials {
                username = System.getenv("GITHUB_USER")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
dependencies {
    implementation(kotlin("script-runtime"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1") // JUnit 5
    compileOnly ("org.projectlombok:lombok:1.18.22")
    annotationProcessor ("org.projectlombok:lombok:1.18.22")
    implementation("org.javatuples:javatuples:1.2")
}
tasks.test {
    useJUnitPlatform()
}
