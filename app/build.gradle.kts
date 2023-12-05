plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("plugin.serialization") version Versions.kotlin
}

android {
    namespace = "com.fandom.fandom.quiz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fandom.fandom.quiz"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {

        getByName("debug")
        {
            storeFile = file("../keystore_debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(platform(Deps.firebaseBoM))
    implementation(Deps.firebaseMessaging)
    implementation("com.google.firebase:firebase-firestore")

    implementation(Deps.androidxCore)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation(Deps.androidxAppcompat)
    implementation(Deps.androidxConstraintlayout)
    implementation(Deps.androidMaterial)
    implementation(Deps.androidxRecyclerview)

    //AUTH
    implementation(Deps.playServicesAuth)

    //KOIN
    implementation(Deps.koin)

    //coroutines
    implementation(Deps.kotlinxCoroutinesAndroid)

    //ViewModel
    implementation(Deps.androidxLifecycleViewmodelKtx)

    //ktor
    implementation(KmmDependecies.Common.Ktor.core)
    implementation(KmmDependecies.Common.Ktor.content)
    implementation(KmmDependecies.Common.Ktor.serializationJson)
    implementation(KmmDependecies.Common.Ktor.logging)
    implementation(KmmDependecies.Android.ktorOkHttp)
    implementation(KmmDependecies.kotlinSerializer)

    //navigation
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUI)

    testImplementation("junit:junit:4.13.2")

}