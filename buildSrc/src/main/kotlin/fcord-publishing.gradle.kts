import java.lang.System.getenv
import java.util.Base64

plugins {
    `maven-publish`
    signing
}

fun MavenPublication.registerDokkaJar() = tasks.register<Jar>("${name}DokkaJar") {
        archiveClassifier = "javadoc"
        destinationDirectory = destinationDirectory.get().dir(name)
        from(tasks.named("dokkaHtml"))
    }

publishing {
    publications {
        withType<MavenPublication>().configureEach {
            artifact(registerDokkaJar())
            groupId = Library.group
            artifactId = artifactId
            version = Library.version

            pom {
                name = Library.name
                description = Library.description
                url = Library.projectUrl

                developers {
                    developer {
                        name = "kiNgchev"
                    }
                }

                issueManagement {
                    system = "GitHub"
                    url = "https://github.com/kiNgchev/fcord.kt/issues"
                }

                licenses {
                    license {
                        name = "MIT"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }

                scm {
                    connection = "scm:git:ssh://github.com/kiNgchev/fcord.kt.git"
                    developerConnection = "scm:git:ssh://git@github.com:kiNgchev/fcord.kt.git"
                    url = Library.projectUrl
                }
            }
        }

        repositories {
            maven {
                name = "OSSRH"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = getenv("NEXUS_USER")
                    password = getenv("NEXUS_PASSWORD")
                }
            }
            // А чтоб не втыкал
            //maven {
            //    name = "GitHubPackages"
            //    url = uri("https://maven.pkg.github.com/kiNgchev/fcord.kt")
            //    credentials {
            //        username = getenv("GITHUB_ACTOR")
            //        password = getenv("GITHUB_TOKEN")
            //    }
            //}
        }
    }
}

signing {
    val secretKey = getenv("SIGNING_KEY")?.let { String(Base64.getDecoder().decode(it)) }
    val password = getenv("SIGNING_PASSWORD")
    useInMemoryPgpKeys(secretKey, password)
    sign(publishing.publications)
}