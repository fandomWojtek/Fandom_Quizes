package com.fandom.fandom.quiz.auth

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.AuthScreenFragmentBinding
import com.fandom.fandom.quiz.utils.viewBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : Fragment(R.layout.auth_screen_fragment) {

    val binding by viewBinding(AuthScreenFragmentBinding::bind)

    private val authViewModel by viewModel<AuthViewModel>()
    private val googleApiClientProvider: GoogleApiClientProvider by inject()
    private val googleApiClient: GoogleSignInClient by lazy {
        googleApiClientProvider.createGoogleApiClient()
    }

    private val getGoogleAccount = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            authViewModel.googleLogin(result)
        } else {
            googleApiClientProvider.signOut()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleGoogleLogin()
    }

    private fun handleGoogleLogin() {
        binding.signInWithGoogle.setOnClickListener {
            val googleApiAvailability = GoogleApiAvailability.getInstance()
            val result = googleApiAvailability.isGooglePlayServicesAvailable(requireContext())
            if (ConnectionResult.SUCCESS == result) {
                getGoogleAccount.launch(googleApiClient.signInIntent)
            } else {
                googleApiAvailability.getErrorDialog(this, result, 0)?.show()
            }
        }
    }

}