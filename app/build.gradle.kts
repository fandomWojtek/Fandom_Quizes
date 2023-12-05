plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
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
                storeFile = file("../wikia_debug.keystore")
                storePassword = "android"
                keyAlias = "androiddebugkey"
                keyPassword = "android"
            }
        }
    }

    dependencies {

        implementation(platform(Deps.firebaseBoM))

        implementation(Deps.androidxCore)
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        implementation(Deps.androidxAppcompat)
        implementation(Deps.androidxConstraintlayout)
        implementation(Deps.androidMaterial)
        implementation(Deps.androidxRecyclerview)

        testImplementation("junit:junit:4.13.2")

    }