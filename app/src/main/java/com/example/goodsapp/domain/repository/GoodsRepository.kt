package com.example.goodsapp.domain.repository

import com.example.goodsapp.domain.model.Asset
import com.example.goodsapp.domain.model.AssetLocation
import com.example.goodsapp.domain.model.AssetStatus
import com.example.goodsapp.domain.model.PaginatedData
import com.example.goodsapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GoodsRepository {
    suspend fun login(email: String, password: String): Flow<Result<User>>
    suspend fun getAssetsByStatus(): Flow<Result<List<AssetStatus>>>
    suspend fun getAssetsByLocation(): Flow<Result<List<AssetLocation>>>
    suspend fun getAssets(page: Int, pageSize: Int?, search: String?): Flow<Result<PaginatedData<Asset>>>
    suspend fun getAssetDetail(id: String): Flow<Result<Asset>>
    suspend fun createAsset(name: String, statusId: String, locationId: String): Flow<Result<Asset>>
    suspend fun updateAsset(id: String, name: String, statusId: String, locationId: String): Flow<Result<Asset>>
    suspend fun deleteAsset(id: String): Flow<Result<Unit>>
}