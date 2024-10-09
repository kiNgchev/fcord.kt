plugins {
    org.jetbrains.kotlin.jvm
    org.jetbrains.kotlin.plugin.serialization
    org.jetbrains.dokka
    `maven-publish`
}

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()

    jvmToolchain(Jvm.target)

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    publishing {
        publications.register<MavenPublication>(Library.name) {
            from(components["java"])
            artifact(tasks.kotlinSourcesJar)
        }
    }
}