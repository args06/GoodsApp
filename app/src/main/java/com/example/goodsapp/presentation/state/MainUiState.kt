package com.example.goodsapp.presentation.state

import com.example.goodsapp.domain.model.AssetLocation
import com.example.goodsapp.domain.model.AssetStatus

sealed class MainUiState {
    object Initial : MainUiState()
    object Loading : MainUiState()
    data class Success(
        val assetStatus: List<AssetStatus>,
        val assetLocation: List<AssetLocation>
    ) : MainUiState()
    data class Error(val message: String) : MainUiState()
}