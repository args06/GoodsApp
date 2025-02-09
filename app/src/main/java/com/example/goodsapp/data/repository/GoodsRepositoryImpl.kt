package com.example.goodsapp.data.repository

import com.example.goodsapp.data.local.UserPreferencesManager
import com.example.goodsapp.data.mapper.toDomain
import com.example.goodsapp.data.remote.api.GoodsApi
import com.example.goodsapp.data.remote.model.request.CreateAssetRequest
import com.example.goodsapp.data.remote.model.request.LoginRequest
import com.example.goodsapp.data.remote.model.request.UpdateAssetRequest
import com.example.goodsapp.data.remote.model.response.ErrorResponse
import com.example.goodsapp.di.qualifier.IoDispatcher
import com.example.goodsapp.domain.model.Asset
import com.example.goodsapp.domain.model.AssetLocation
import com.example.goodsapp.domain.model.AssetStatus
import com.example.goodsapp.domain.model.PaginatedData
import com.example.goodsapp.domain.model.User
import com.example.goodsapp.domain.repository.GoodsRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class GoodsRepositoryImpl @Inject constructor(
    private val api: GoodsApi,
    private val userPreferencesManager: UserPreferencesManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GoodsRepository {

    override suspend fun login(email: String, password: String): Flow<Result<User>> = flow {
        try {
            val response = api.login(LoginRequest(email, password))
            userPreferencesManager.saveToken(response.token)
            val user = response.toDomain()
            userPreferencesManager.saveUser(user)
            emit(Result.success(user))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = try {
                Gson().fromJson(errorBody, ErrorResponse::class.java)
            } catch (parseException: Exception) {
                ErrorResponse(message = "An unknown error occurred")
            }

            emit(Result.failure(Exception(errorResponse.message)))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(ioDispatcher)

    private fun <T> authenticatedFlow(block: suspend (String) -> T): Flow<Result<T>> = flow {
        try {
            val token = userPreferencesManager.getToken()
                ?: throw Exception("Not authenticated")
            println("Full token: Bearer $token")
            val result = block("Bearer $token")
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(ioDispatcher)

    override suspend fun getAssetsByStatus(): Flow<Result<List<AssetStatus>>> =
        authenticatedFlow { token ->
            val response = api.getAssetByStatus(token)
            response.data?.map { it.toDomain() } ?: emptyList()
        }

    override suspend fun getAssetsByLocation(): Flow<Result<List<AssetLocation>>> =
        authenticatedFlow { token ->
            val response = api.getAssetByLocation(token)
            response.data?.map { it.toDomain() } ?: emptyList()
        }

    override suspend fun getAssets(
        page: Int,
        pageSize: Int?,
        search: String?
    ): Flow<Result<PaginatedData<Asset>>> = authenticatedFlow { token ->
        val response = api.getAllAssets(token, page, pageSize, search)
        response.data?.let { responseData ->
            PaginatedData(
                items = responseData.items.map { it.toDomain() },
                total = responseData.total,
                page = responseData.page,
                size = responseData.size,
                totalPages = responseData.totalPages
            )
        } ?: PaginatedData(emptyList(), 0, page, pageSize ?: 0, 0)
    }

    override suspend fun getAssetDetail(id: String): Flow<Result<Asset>> = authenticatedFlow { token ->
        val response = api.getAssetDetail(token, id)
        response.data?.toDomain() ?: throw Exception("Asset not found")
    }

    override suspend fun createAsset(
        name: String,
        statusId: String,
        locationId: String
    ): Flow<Result<Asset>> = authenticatedFlow { token ->
            val response = api.createAsset(token, CreateAssetRequest(name, statusId, locationId))
            response.data?.toDomain() ?: throw IllegalStateException("Asset creation failed")
        }

    override suspend fun updateAsset(
        id: String,
        name: String,
        statusId: String,
        locationId: String
    ): Flow<Result<Asset>> =
        authenticatedFlow { token ->
            val response = api.updateAsset(token, id, UpdateAssetRequest(name, statusId, locationId))
            response.data?.toDomain() ?: throw IllegalStateException("Asset update failed")
        }

    override suspend fun deleteAsset(id: String): Flow<Result<Unit>> = authenticatedFlow { token ->
        val response = api.deleteAsset(token, id)
        if (response.isSuccessful) {
            Result.success(Unit)
        } else {
            Result.failure(IllegalStateException("Asset deletion failed"))
        }
    }

}