package com.example.goodsapp.domain.usecase.asset

import com.example.goodsapp.domain.model.AssetLocation
import com.example.goodsapp.domain.repository.GoodsRepository
import com.example.goodsapp.domain.usecase.base.BaseUseCaseNoParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAssetsByLocationUseCase : BaseUseCaseNoParams<List<AssetLocation>>

class GetAssetsByLocationUseCaseImpl @Inject constructor(
    private val repository: GoodsRepository
) : GetAssetsByLocationUseCase {

    override suspend fun invoke(): Flow<Result<List<AssetLocation>>> =
        repository.getAssetsByLocation()
}