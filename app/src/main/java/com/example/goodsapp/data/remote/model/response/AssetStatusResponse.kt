package com.example.goodsapp.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class AssetStatusResponse(
    @SerializedName("status") val status: StatusResponse,
    @SerializedName("count") val count: Int
)