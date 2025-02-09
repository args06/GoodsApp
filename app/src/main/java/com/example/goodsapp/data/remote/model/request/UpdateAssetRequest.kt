package com.example.goodsapp.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class UpdateAssetRequest(
    @SerializedName("name") val name: String,
    @SerializedName("status_id") val statusId: String,
    @SerializedName("location_id") val locationId: String
)