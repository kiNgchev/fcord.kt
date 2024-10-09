plugins {
    org.jetbrains.dokka
    `fcord-module`
    `fcord-publishing`
}

dependencies {
    implementation(libs.bundles.ktor.client)
}