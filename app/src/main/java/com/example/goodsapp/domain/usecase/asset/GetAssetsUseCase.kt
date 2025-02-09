package com.example.goodsapp.domain.usecase.asset

import com.example.goodsapp.domain.model.Asset
import com.example.goodsapp.domain.model.PaginatedData
import com.example.goodsapp.domain.repository.GoodsRepository
import com.example.goodsapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAssetsUseCase : BaseUseCase<GetAssetsUseCase.Params, PaginatedData<Asset>> {
    data class Params(
        val page: Int,
        val pageSize: Int? = null,
        val search: String? = null
    )
}

class GetAssetsUseCaseImpl @Inject constructor(
    private val repository: GoodsRepository
) : GetAssetsUseCase {

    override suspend fun invoke(parameters: GetAssetsUseCase.Params): Flow<Result<PaginatedData<Asset>>> =
        repository.getAssets(parameters.page, parameters.pageSize, parameters.search)
}