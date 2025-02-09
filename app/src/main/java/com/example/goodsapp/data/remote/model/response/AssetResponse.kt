package com.example.goodsapp.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class AssetResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: StatusResponse,
    @SerializedName("location") val location: LocationResponse,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class StatusResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

data class LocationResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)