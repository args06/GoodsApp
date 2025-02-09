package com.example.goodsapp.presentation.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodsapp.di.qualifier.MainDispatcher
import com.example.goodsapp.domain.usecase.asset.GetAssetsByLocationUseCase
import com.example.goodsapp.domain.usecase.asset.GetAssetsByStatusUseCase
import com.example.goodsapp.presentation.state.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val getAssetsByLocationUseCase: GetAssetsByLocationUseCase,
    private val getAssetsByStatusUseCase: GetAssetsByStatusUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Initial)
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(mainDispatcher) {
            _uiState.value = MainUiState.Loading

            combine(
                getAssetsByStatusUseCase(),
                getAssetsByLocationUseCase()
            ) { statusResult, locationResult ->
                when {
                    statusResult.isSuccess && locationResult.isSuccess -> {
                        MainUiState.Success(
                            assetStatus = statusResult.getOrNull() ?: emptyList(),
                            assetLocation = locationResult.getOrNull() ?: emptyList()
                        )
                    }
                    statusResult.isFailure -> {
                        MainUiState.Error(
                            statusResult.exceptionOrNull()?.message ?: "Unknown error"
                        )
                    }
                    locationResult.isFailure -> {
                        MainUiState.Error(
                            locationResult.exceptionOrNull()?.message ?: "Unknown error"
                        )
                    }
                    else -> MainUiState.Error("Unknown error occurred")
                }
            }.catch { error ->
                _uiState.value = MainUiState.Error(error.message ?: "Unknown error")
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun retry() {
        loadData()
    }
}