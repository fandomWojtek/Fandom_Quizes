repositories {
    google()
    mavenCentral()
}
plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("com.android.tools.build:gradle:8.1.1")
    implementation(kotlin("gradle-plugin", "1.9.10"))
}