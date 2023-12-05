package com.fandom.fandom.quiz.networking

import android.os.Build

import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

internal fun OkHttpClient.Builder.acceptAllCerts() {
    fun trustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                // nop
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                // nop
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return emptyArray()
            }
        }
    }

    fun isEmulator(): Boolean {
        return Build.FINGERPRINT.contains("generic")
                || Build.FINGERPRINT.contains("emulator64")
                || Build.FINGERPRINT.contains("emulator_arm64")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk" == Build.PRODUCT
    }

    if ( isEmulator()) {
        val sslContext = SSLContext.getInstance("SSL")
        val trustManager = trustManager()
        sslContext.init(null, arrayOf(trustManager), SecureRandom())

        sslSocketFactory(sslContext.socketFactory, trustManager)
        hostnameVerifier { _, _ -> true }
    }
}
