plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    api(project(":core:domain"))
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.arrow.core)
    implementation(libs.kotlin.coroutines.core)
}
