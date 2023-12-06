package com.fandom.fandom.quiz.networking


import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.koin.dsl.module

const val TAG = "QUIZ_KTOR"
fun networkModule(apiUrl: String, devBuilds: Boolean = false) = module {
    single {
        Json {
            encodeDefaults = true
            prettyPrint = true
            ignoreUnknownKeys = true
            explicitNulls = false
            ignoreUnknownKeys = true
        }
    }
    single {
        HttpClient(engine =  OkHttp.create {
            preconfigured = OkHttpClient.Builder().also {
                if (devBuilds) {
                    it.acceptAllCerts()
                }
            }.build()}) {

            expectSuccess = true

            install(ContentNegotiation) {
                json(get<Json>())
            }

            if (devBuilds) {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            message.split("\n").forEach {
                                android.util.Log.i(TAG, it)
                            }
                        }
                    }
                    level = LogLevel.ALL
                }
            }

            HttpResponseValidator {

                handleResponseExceptionWithRequest { exception, _ ->
                    android.util.Log.i(TAG, exception.stackTraceToString())
                }
            }

            defaultRequest {
                val baseUrl = Url(apiUrl)
                host = baseUrl.host
                url {
                    protocol = baseUrl.protocol
                }
                contentType(ContentType.Application.Json)
            }

        }
    }
}