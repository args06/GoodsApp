package com.example.goodsapp.presentation.state

import com.example.goodsapp.domain.model.Asset
import com.example.goodsapp.domain.model.PaginatedData

sealed class AssetsUiState {
    object Initial : AssetsUiState()
    object Loading : AssetsUiState()
    data class Success(val assets: PaginatedData<Asset>) : AssetsUiState()
    data class Error(val message: String) : AssetsUiState()
}