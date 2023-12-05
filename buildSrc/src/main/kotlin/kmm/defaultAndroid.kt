package kmm

import com.android.build.api.dsl.LibraryExtension
import org.gradle.internal.impldep.org.fusesource.jansi.AnsiRenderer.test
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

fun LibraryExtension.defaultAndroidKmmLibrary() {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}

fun LibraryExtension.defaultAndroidibrary() {
    defaultAndroidKmmLibrary()

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

