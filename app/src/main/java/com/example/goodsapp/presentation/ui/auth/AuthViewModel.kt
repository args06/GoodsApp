package com.example.goodsapp.presentation.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodsapp.di.qualifier.MainDispatcher
import com.example.goodsapp.domain.usecase.auth.LoginUseCase
import com.example.goodsapp.presentation.state.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val authState = _authState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch(mainDispatcher) {
            _authState.value = AuthUiState.Loading
            val params = LoginUseCase.Params(email, password)
            loginUseCase(params)
                .catch { error ->
                    _authState.value = AuthUiState.Error(error.message ?: "Unknown error")
                }
                .collect { result ->
                    result.fold(
                        onSuccess = { user ->
                            _authState.value = AuthUiState.Success(user)
                        },
                        onFailure = { error ->
                            print("Error: ${error}")
                            print("Error: ${error.localizedMessage}")
                            _authState.value = AuthUiState.Error(error.localizedMessage ?: "Unknown error")
                        }
                    )
                }
        }
    }
}