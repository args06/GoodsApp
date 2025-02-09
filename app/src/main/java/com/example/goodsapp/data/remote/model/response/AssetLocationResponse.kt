package com.example.goodsapp.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class AssetLocationResponse(
    @SerializedName("location") val location: LocationResponse,
    @SerializedName("count") val count: Int
)