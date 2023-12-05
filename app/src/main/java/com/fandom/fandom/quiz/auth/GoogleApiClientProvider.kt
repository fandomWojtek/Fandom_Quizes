package com.fandom.fandom.quiz.auth

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class GoogleApiClientProvider(private val context: Context) {

    private val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("530768210634-ffhulcgpbgfj9jg5e67oc2hjh71a9lbf.apps.googleusercontent.com")
        .requestEmail()
        .build()
    private val mGoogleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
    private var account: GoogleSignInAccount? = null
    init {
        account = GoogleSignIn.getLastSignedInAccount(context)
    }

    private fun getLastSignedAccount() {
        account = GoogleSignIn.getLastSignedInAccount(context)
    }

    fun createGoogleApiClient(): GoogleSignInClient {
        return mGoogleSignInClient
    }

    fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnSuccessListener {}
            .addOnFailureListener {}
    }

}

val googleApiClientProvider = module {
    factory { GoogleApiClientProvider(androidContext())}
}
