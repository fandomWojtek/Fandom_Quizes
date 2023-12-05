import KmmVersions.dateTimeVersion
import KmmVersions.graphQLVersion
import KmmVersions.kermitVersion
import KmmVersions.ktorVersion
import KmmVersions.settingsVersion
import KmmVersions.slf4jVersion
import KmmVersions.kryptoVersion
import KmmVersions.sqlDelightVersion


object KmmVersions {
    val ktorVersion = "2.3.2"
    val mockkKmmVersion = "1.11.0"
    val settingsVersion = "1.0.0"
    val kotlinSerializer = "1.5.1"
    val dateTimeVersion = "0.4.0"
    val slf4jVersion = "1.6.1"
    val kermitVersion = "2.0.0-RC4"
    val kryptoVersion = "4.0.7"
    val oneTrustVersion = "202308.1.0.0"
    const val sqlDelightVersion = "2.0.0-rc02"
    val graphQLVersion = "3.8.1"
    val coilVersion = "2.4.0"
    val googleKspVersion= "1.9.10-1.0.13"
    //moko resource
    const val mokoResourceVersion = "0.23.0"
    const val composeMultiCompiler = "1.5.2"
}

object KmmDependecies {
    val kotlinSerializer = "org.jetbrains.kotlinx:kotlinx-serialization-json:${KmmVersions.kotlinSerializer}"
    val mokoResource = "dev.icerock.moko:resources-generator:${KmmVersions.mokoResourceVersion}"
    val mokoPlugin = "dev.icerock.mobile.multiplatform-resources"

    object Common {
        val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx}"
        val koinCore = "io.insert-koin:koin-core:${Versions.koinVersion}"
        val settings = "com.russhwolf:multiplatform-settings:$settingsVersion"
        val settingsSerializer = "com.russhwolf:multiplatform-settings-serialization:$settingsVersion"
        val settingsCoroutines = "com.russhwolf:multiplatform-settings-coroutines:$settingsVersion"
        val kotlinDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion"
        val logger = "co.touchlab:kermit:$kermitVersion"
        val krypto = "com.soywiz.korlibs.krypto:krypto:$kryptoVersion"
        val sqlDelightNativeDriver = "app.cash.sqldelight:native-driver:$sqlDelightVersion"
        const val sqlDelightCoroutines = "app.cash.sqldelight:coroutines-extensions:$sqlDelightVersion"
        const val sqlDelightAdapters = "app.cash.sqldelight:primitive-adapters:$sqlDelightVersion"

        val graphQL = "com.apollographql.apollo3:apollo-runtime:$graphQLVersion"
        val graphQLAdapters = "com.apollographql.apollo3:apollo-adapters:$graphQLVersion"

        //moko resource
        val mokoResource = "dev.icerock.moko:resources:${KmmVersions.mokoResourceVersion}"
        val mokoCompose = "dev.icerock.moko:resources-compose:${KmmVersions.mokoResourceVersion}"
        object Ktor {
            val core = "io.ktor:ktor-client-core:$ktorVersion"
            val content = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
            val serializationJson = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
            val auth = "io.ktor:ktor-client-auth:$ktorVersion"
            val logging = "io.ktor:ktor-client-logging:$ktorVersion"
            val mock = "io.ktor:ktor-client-mock:$ktorVersion"
            val date = "io.ktor:ktor-server-data-conversion:$ktorVersion"

        }

        //TESTING
        val mockk = "io.mockk:mockk:${Versions.mockk}"
        val testSettings = "com.russhwolf:multiplatform-settings-test:$settingsVersion"
        val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinx}"
        val objenesis = "org.objenesis:objenesis:3.3"

    }

    object Android {
        val coroutineAndroid = Deps.kotlinxCoroutinesAndroid
        val ktorOkHttp = "io.ktor:ktor-client-okhttp:$ktorVersion"
        val koinAndroid = Deps.koin
        val oneTrust = "com.onetrust.cmp:native-sdk:${KmmVersions.oneTrustVersion}"
        const val sqlDelightAndroidDriver = "app.cash.sqldelight:android-driver:$sqlDelightVersion"
        val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koinComposeVersion}"
        val coilCompose = "io.coil-kt:coil-compose:${KmmVersions.coilVersion}"
        const val sqlDelightTestAndroid = "app.cash.sqldelight:sqlite-driver:$sqlDelightVersion"
    }

    object iOS {
        val ktorDarwin = "io.ktor:ktor-client-darwin:$ktorVersion"
    }
}