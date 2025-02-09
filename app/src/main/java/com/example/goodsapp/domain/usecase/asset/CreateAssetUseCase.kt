package com.example.goodsapp.domain.usecase.asset

import com.example.goodsapp.domain.model.Asset
import com.example.goodsapp.domain.repository.GoodsRepository
import com.example.goodsapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CreateAssetUseCase : BaseUseCase<CreateAssetUseCase.Params, Asset> {
    data class Params(
        val name: String,
        val statusId: String,
        val locationId: String
    )
}

class CreateAssetUseCaseImpl @Inject constructor(
    private val repository: GoodsRepository
) : CreateAssetUseCase {

    override suspend fun invoke(parameters: CreateAssetUseCase.Params): Flow<Result<Asset>> =
        repository.createAsset(parameters.name, parameters.statusId, parameters.locationId)
}