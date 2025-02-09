package com.example.goodsapp.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodsapp.domain.model.User
import com.example.goodsapp.domain.usecase.auth.GetCurrentUserUseCase
import com.example.goodsapp.domain.usecase.auth.IsUserLoggedInUseCase
import com.example.goodsapp.domain.usecase.auth.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _isLoggedOut = MutableStateFlow<Boolean?>(null)
    val isLoggedOut: StateFlow<Boolean?> = _isLoggedOut.asStateFlow()

    init {
        observeLoginState()
        observeCurrentUser()
    }

    private fun observeLoginState() {
        viewModelScope.launch {
            isUserLoggedInUseCase().collect { result ->
                result.onSuccess {
                    _isLoggedIn.value = it
                }.onFailure {
                    _isLoggedIn.value = null
                }
            }
        }
    }

    private fun observeCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect { result ->
                result.onSuccess {
                    _currentUser.value = it
                }.onFailure {
                    _currentUser.value = null
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase().collect { result ->
                result.onSuccess {
                    _isLoggedOut.value = it
                }.onFailure {
                    _currentUser.value = null
                }
            }
        }
    }
}