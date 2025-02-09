package com.example.goodsapp.domain.usecase.asset

import com.example.goodsapp.domain.model.AssetStatus
import com.example.goodsapp.domain.repository.GoodsRepository
import com.example.goodsapp.domain.usecase.base.BaseUseCaseNoParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAssetsByStatusUseCase : BaseUseCaseNoParams<List<AssetStatus>>

class GetAssetsByStatusUseCaseImpl @Inject constructor(
    private val repository: GoodsRepository
) : GetAssetsByStatusUseCase {

    override suspend fun invoke(): Flow<Result<List<AssetStatus>>> =
        repository.getAssetsByStatus()
}