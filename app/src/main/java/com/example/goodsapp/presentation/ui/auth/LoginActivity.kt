package com.example.goodsapp.presentation.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.goodsapp.databinding.ActivityLoginBinding
import com.example.goodsapp.presentation.state.AuthUiState
import com.example.goodsapp.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            performLogin()
        }

        observeAuthState()
    }

    private fun performLogin() {
        val email = binding.emailInput.text.trim()
        val password = binding.etPassword.text.trim()

        binding.emailInput.hideError()
        binding.etPassword.hideError()

        val emailError = when {
            email.isEmpty() -> "This form is required"
            !isValidEmail(email) -> "Invalid email!"
            else -> null
        }

        val passwordError = when {
            password.isEmpty() -> "This form is required"
            else -> null
        }

        emailError?.let { binding.emailInput.showError(it) }
        passwordError?.let { binding.etPassword.showError(it) }

        if (emailError != null || passwordError != null) return

        viewModel.login(email, password)
    }

    private fun observeAuthState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.authState.collect { state ->
                    when (state) {
                        is AuthUiState.Initial -> {
                            setLoadingState(false)
                        }
                        is AuthUiState.Loading -> {
                            setLoadingState(true)
                        }
                        is AuthUiState.Success -> {
                            setLoadingState(false)
                            MainActivity.start(this@LoginActivity)
                        }
                        is AuthUiState.Error -> {
                            setLoadingState(false)
                            binding.etPassword.showError(state.message)
                        }
                    }
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun setLoadingState(isLoading: Boolean) {
        binding.btnLogin.isEnabled = !isLoading
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}