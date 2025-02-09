package com.example.goodsapp.data.remote.api

import com.example.goodsapp.data.remote.model.request.CreateAssetRequest
import com.example.goodsapp.data.remote.model.request.LoginRequest
import com.example.goodsapp.data.remote.model.request.UpdateAssetRequest
import com.example.goodsapp.data.remote.model.response.ApiResponse
import com.example.goodsapp.data.remote.model.response.AssetLocationResponse
import com.example.goodsapp.data.remote.model.response.AssetResponse
import com.example.goodsapp.data.remote.model.response.AssetStatusResponse
import com.example.goodsapp.data.remote.model.response.LoginResponse
import com.example.goodsapp.data.remote.model.response.PaginatedResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface GoodsApi {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @Headers("Accept: application/json")
    @GET("home/agg-asset-by-status")
    suspend fun getAssetByStatus(
        @Header("Authorization") token: String
    ): ApiResponse<List<AssetStatusResponse>>

    @GET("home/agg-asset-by-location")
    suspend fun getAssetByLocation(
        @Header("Authorization") token: String
    ): ApiResponse<List<AssetLocationResponse>>

    @GET("asset")
    suspend fun getAllAssets(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int?,
        @Query("search") search: String?
    ): ApiResponse<PaginatedResponse<AssetResponse>>

    @GET("asset/{id}")
    suspend fun getAssetDetail(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): ApiResponse<AssetResponse>

    @POST("asset")
    suspend fun createAsset(
        @Header("Authorization") token: String,
        @Body request: CreateAssetRequest
    ): ApiResponse<AssetResponse>

    @PUT("asset/{id}")
    suspend fun updateAsset(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body request: UpdateAssetRequest
    ): ApiResponse<AssetResponse>

    @DELETE("asset/{id}")
    suspend fun deleteAsset(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<Unit>
}