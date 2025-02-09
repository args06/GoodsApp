package com.example.goodsapp.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("status") val status: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: T?,
    @SerializedName("results") val results: T?
)