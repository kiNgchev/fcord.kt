> [!WARNING]
> **LIBRARY WAS RELEASED BEFORE RELEASE FCORD**
> So, this library has no actuality before release Fcord monitoring
# Fcord.kt

Fcord.kt is a coroutine-based implementation of Fcord API, written 100% in Kotlin

---
## Useful links
- [Docs](https://kingchev.github.io/Fcord.kt/)
- [Wiki](https://github.com/kiNgchev/fcord.kt/wiki)

---
## Platforms support
| Module                   | JVM | JS | Native |
|--------------------------|-----|----|--------|
| [common](./fcord-common) | ✅   | ❌  | ❌      |

---
## Installation 

---
### Gradle (Kotlin)
Maven central repository
```kotlin
dependencies {
    implementation("net.kingchev:fcord-common:{version}")
}
```
Or Jitpack repository
```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.kiNgchev:fcord.kt:{version}")
}
```
---
### Gradle (Groovy)
Maven central repository
```groovy
dependencies {
    implementation("net.kingchev:fcord-common:{version}")
}
```
Or Jitpack repository
```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}


dependencies {
    implementation 'com.github.kiNgchev:fcord.kt:{version}'
}
```
---
### Maven
Maven central repository
```xml
<dependency>
    <groupId>net.kingchev</groupId>
    <artifactId>fcord-common</artifactId>
    <version>{version}</version>
</dependency>
```
Or Jitpack repository
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
```xml
<dependency>
    <groupId>com.github.kiNgchev</groupId>
    <artifactId>fcord.kt</artifactId>
    <version>{version}</version>
</dependency>
```
---
## example
```kotlin
suspend fun main() {
    val stats = BotStats()
        .servers(7)
        .shards(1)

    mircord("Place your Mircord API token here") {
        updateStatistic(stats)
    }
}
```
---
## This project is supported by JetBrains
[![JetBrains Logo (Main) logo](https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg)](https://jb.gg/OpenSourceSupport)