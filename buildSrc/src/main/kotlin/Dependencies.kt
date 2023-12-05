@file:Suppress("SpellCheckingInspection", "MayBeConstant")

import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 26
    const val compileSdk = 34
    const val targetSdk = 33
    val javaVersion = JavaVersion.VERSION_17
    val javaVersionInt = 17
}

object Versions {

    //gradle
    const val gradlePlugin = "8.1.1"
    const val firebaseCrashlyticsGradle = "2.9.6"
    const val playPublisher = "3.8.4"
    const val versionsGradlePlugin = "0.47.0"
    const val sonarqubeGradlePlugin = "4.2.1.3168"
    const val composePlugin = "1.5.1"

    //kotlin
    const val kotlin = "1.9.10"
    const val kotlinx = "1.7.2"

    //androidx
    const val androidxAppcompat = "1.6.1"
    const val androidxCore = "1.9.0"
    const val androidxRecyclerview = "1.3.1"
    const val viewPager2 = "1.1.0-beta02"
    const val androidxConstraintlayout = "2.1.4"
    const val androidxLifecycle = "2.6.1"
    const val androidxActivityVersion = "1.7.2"
    const val androidxFragmentVersion = "1.6.0"
    const val androidxRoom = "2.6.0-beta01"
    const val androidxCardView = "1.0.0"
    const val androidxSplashScreen = "1.0.1"
    const val androidxLocalBroadcastManager = "1.1.0"
    const val androidxWork = "2.8.1"
    const val exifinterface = "1.3.6"
    const val navigation = "2.7.5"
    const val security = "1.1.0-alpha06"
    const val lifecycleProcess = "2.6.1"
    const val composeUi = "1.5.1"

    //androidx test
    const val androidxArchCore = "2.1.0"
    const val androidxTest = "1.5.0"
    const val androidxTestEspresso = "3.5.1"
    const val androidxTestEspressoContrib = "3.3.0"
    const val androidxTestUiAutomator = "2.2.0"
    const val orchestrator = "1.4.2"

    //test
    const val junit = "4.13.2"
    const val junitKtx = "1.1.5"
    const val mockito = "5.4.0"
    const val mockitoKotlin = "5.0.0"
    const val mockk = "1.13.3" // newer version (1.13.4) causes UI tests to fail due to java.lang.IncompatibleClassChangeError https://github.com/mockk/mockk/issues/1035
    const val junit5 = "5.9.3"

    //google
    const val googleGms = "4.3.15"

    //google android
    const val androidGmsAnalytics = "18.0.3"
    const val androidGmsAuth = "20.6.0"
    const val androidMaterial = "1.9.0"

    //firebase
    const val firebaseBoM = "32.6.0"

    //amplitude
    const val amplitude = "1.7.1"

    //rx
    const val rxBinding = "3.1.0"
    const val rxAndroid = "2.1.1"
    const val rxJava = "2.2.21"
    const val rxIdler = "0.11.0"

    //rest
    const val glide = "4.15.1"
    const val retrofit = "2.9.0"
    const val kotlinSerializationConverter = "1.0.0"
    const val okhttp = "4.11.0"
    const val flexbox = "2.0.1"
    const val moshi = "1.15.0"
    const val photoView = "2.3.0"
    const val jwPlayer = "4.12.2"
    const val facebookLogin = "16.1.2"
    const val androidPlayCore = "1.10.3"
    const val guava = "23.0"
    const val annotationsJava = "21.0.1"
    const val cropper = "1.0.1"
    const val desurging = "2.0.3"
    //koin
    const val koinVersion = "3.4.2"
    const val koinComposeVersion = "3.4.5"
    const val koinTestVersion = "3.4.1"

    //datetime
    const val dateTimeVersion = "0.4.0"

    const val lottieVersion = "6.0.1"
    const val quickieVersion = "1.7.0" //qr code scanner
    const val uxcamVersion = "3.6.13"

    const val kotlinCompilerExtensionVersion = "1.5.3"

    const val youTubePlayer = "12.1.0"
}

object Deps {

    //gradle
    const val gradle = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val firebaseCrashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlyticsGradle}"
    const val playPublisher = "com.github.triplet.gradle:play-publisher:${Versions.playPublisher}"
    const val versionsGradlePlugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.versionsGradlePlugin}"
    const val versionsSafeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val sonarqubeGradlePlugin = "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${Versions.sonarqubeGradlePlugin}"
    const val composePlugin = "org.jetbrains.compose:compose-gradle-plugin:${Versions.composePlugin}"


    //kotlin
    const val kotlinJdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinx}"
    const val kotlinxCoroutinesRx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:${Versions.kotlinx}"
    const val kotlinxCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinx}"
    const val kotlinDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.dateTimeVersion}"

    //google
    const val googleServices = "com.google.gms:google-services:${Versions.googleGms}"

    //google android
    const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
    const val playServicesAuth = "com.google.android.gms:play-services-auth:${Versions.androidGmsAuth}"
    const val playServicesAnalytics = "com.google.android.gms:play-services-analytics:${Versions.androidGmsAnalytics}"

    //androidx
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val androidxLifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifecycleProcess}"
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Versions.androidxAppcompat}"
    const val androidxLocalBroadcastManager = "androidx.localbroadcastmanager:localbroadcastmanager:${Versions.androidxLocalBroadcastManager}"
    const val androidxWork = "androidx.work:work-runtime:${Versions.androidxWork}"
    const val exifinterface = "androidx.exifinterface:exifinterface:${Versions.exifinterface}"
    const val securityCryptoX = "androidx.security:security-crypto-ktx:${Versions.security}"
    const val androidxComposeUi = "androidx.compose.ui:ui:${Versions.composeUi}"

    //androidx view
    const val androidxConstraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintlayout}"
    const val androidxRecyclerview = "androidx.recyclerview:recyclerview:${Versions.androidxRecyclerview}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val androidxCardView = "androidx.cardview:cardview:${Versions.androidxCardView}"
    const val androidxSplashScreen = "androidx.core:core-splashscreen:${Versions.androidxSplashScreen}"

    //androidx lifecycle
    const val androidXLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
    const val androidxLifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycle}"
    const val androidxLifecycleViewmodelComposeKtx = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.androidxLifecycle}"
    const val androidxLifecycleRuntimeComposeKtx = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.androidxLifecycle}"
    const val androidxActivityKtx = "androidx.activity:activity-ktx:${Versions.androidxActivityVersion}"
    const val androidxActivity = "androidx.activity:activity:${Versions.androidxActivityVersion}"
    const val androidxFragmentKtx = "androidx.fragment:fragment-ktx:${Versions.androidxFragmentVersion}"


    //androidx room
    const val androidxRoomRuntime = "androidx.room:room-runtime:${Versions.androidxRoom}"
    const val androidxRoomKtx = "androidx.room:room-ktx:${Versions.androidxRoom}"
    const val androidxRoomCompiler = "androidx.room:room-compiler:${Versions.androidxRoom}"
    const val androidxRoomTesting = "androidx.room:room-testing:${Versions.androidxRoom}"
    const val androidxRoomRxJava2 = "androidx.room:room-rxjava2:${Versions.androidxRoom}"

    //androidx jetpack navigation
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    //okhttp3
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val kotlinSerializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinSerializationConverter}"
    const val retrofitConverterScalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"
    const val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"

    //glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotations = "com.github.bumptech.glide:annotations:${Versions.glide}"
    const val glideOkHttpIntegration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}@aar"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    //firebase
    const val firebaseBoM = "com.google.firebase:firebase-bom:${Versions.firebaseBoM}"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseConfig = "com.google.firebase:firebase-config-ktx"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging"
    const val firebaseInAppMessaging = "com.google.firebase:firebase-inappmessaging-display-ktx"

    //Amplitube
    const val amplitube = "com.amplitude:analytics-android:${Versions.amplitude}"

    //rest
    const val flexbox = "com.google.android:flexbox:${Versions.flexbox}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    const val photoView = "com.github.chrisbanes:PhotoView:${Versions.photoView}"

    //androidx test
    const val androidxArchCore = "androidx.arch.core:core-testing:${Versions.androidxArchCore}"
    const val androidxTestRunner = "androidx.test:runner:${Versions.androidxTest}"
    const val androidxTestCore = "androidx.test:core:${Versions.androidxTest}"
    const val androidxTestCoreKtx = "androidx.test:core-ktx:${Versions.androidxTest}"

    const val androidxTestRules = "androidx.test:rules:${Versions.androidxTest}"
    const val androidxEspressoCore = "androidx.test.espresso:espresso-core:${Versions.androidxTestEspresso}"
    const val androidxEspressoIntents = "androidx.test.espresso:espresso-intents:${Versions.androidxTestEspresso}"
    const val androidxEspressoWeb = "androidx.test.espresso:espresso-web:${Versions.androidxTestEspresso}"
    const val androidxUiAutomator = "androidx.test.uiautomator:uiautomator:${Versions.androidxTestUiAutomator}"
    const val androidxEspressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.androidxTestEspressoContrib}"
    const val androidxOrchestrator = "androidx.test:orchestrator:${Versions.orchestrator}"

    //test
    const val junit = "junit:junit:${Versions.junit}"
    const val junitKtx = "androidx.test.ext:junit-ktx:${Versions.junitKtx}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"

    const val runtimeJunit5 = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    const val junit5 = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    const val junit5Params = "org.junit.jupiter:junit-jupiter-params:${Versions.junit5}"

    //rx
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxIdler = "com.squareup.rx.idler:rx2-idler:${Versions.rxIdler}"
    const val rxBindingCore = "com.jakewharton.rxbinding3:rxbinding-core:${Versions.rxBinding}"
    const val rxBindingViewPager = "com.jakewharton.rxbinding3:rxbinding-viewpager:${Versions.rxBinding}"
    const val rxBindingAppCompat = "com.jakewharton.rxbinding3:rxbinding-appcompat:${Versions.rxBinding}"
    const val rxBindingRecyclerView = "com.jakewharton.rxbinding3:rxbinding-recyclerview:${Versions.rxBinding}"
    const val rxBindingSwipeRefreshLayout = "com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:${Versions.rxBinding}"
    const val rxBindingMaterial = "com.jakewharton.rxbinding3:rxbinding-material:${Versions.rxBinding}"

    //rest
    const val jwPlayerCore = "com.jwplayer:jwplayer-core:${Versions.jwPlayer}"
    const val jwPlayerCommon = "com.jwplayer:jwplayer-common:${Versions.jwPlayer}"
    const val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebookLogin}"
    const val androidPlayCore = "com.google.android.play:core:${Versions.androidPlayCore}"
    const val guava = "com.google.guava:guava:${Versions.guava}"
    const val guavaGoogleFix = "com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava"
    const val cropper = "com.edmodo:cropper:${Versions.cropper}"
    const val desurging = "com.android.tools:desugar_jdk_libs:${Versions.desurging}"
    const val youtubePlayer ="com.pierfrancescosoffritti.androidyoutubeplayer:core:${Versions.youTubePlayer}"
    //koin
    const val koin = "io.insert-koin:koin-android:${Versions.koinVersion}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koinTestVersion}"

    const val lottieAnimations = "com.airbnb.android:lottie:${Versions.lottieVersion}"
    const val quickieScannerQR = "io.github.g00fy2.quickie:quickie-bundled:${Versions.quickieVersion}"

    const val uxcam = "com.uxcam:uxcam:${Versions.uxcamVersion}"

    //from kmm
    val ktorTesting = listOf(KmmDependecies.Common.Ktor.mock, KmmDependecies.Common.Ktor.serializationJson, KmmDependecies.Common.Ktor.content)

    const val sqlDelightCoroutines = KmmDependecies.Common.sqlDelightCoroutines
    const val sqlDelightAdapters = KmmDependecies.Common.sqlDelightAdapters
    const val sqlDelightTestAndroid = KmmDependecies.Android.sqlDelightTestAndroid
    const val sqlDelightAndroid = KmmDependecies.Android.sqlDelightAndroidDriver
}
