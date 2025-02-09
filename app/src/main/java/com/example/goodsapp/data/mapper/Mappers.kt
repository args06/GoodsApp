package com.example.goodsapp.data.mapper

import com.example.goodsapp.data.remote.model.response.AssetLocationResponse
import com.example.goodsapp.data.remote.model.response.AssetResponse
import com.example.goodsapp.data.remote.model.response.AssetStatusResponse
import com.example.goodsapp.data.remote.model.response.LocationResponse
import com.example.goodsapp.data.remote.model.response.LoginResponse
import com.example.goodsapp.data.remote.model.response.PaginatedResponse
import com.example.goodsapp.data.remote.model.response.StatusResponse
import com.example.goodsapp.domain.model.Asset
import com.example.goodsapp.domain.model.AssetLocation
import com.example.goodsapp.domain.model.AssetStatus
import com.example.goodsapp.domain.model.Location
import com.example.goodsapp.domain.model.PaginatedData
import com.example.goodsapp.domain.model.Status
import com.example.goodsapp.domain.model.User

fun LoginResponse.toDomain() = User(
    id = id,
    email = email,
    username = username
)

fun StatusResponse.toDomain() = Status(
    id = id,
    name = name
)

fun LocationResponse.toDomain() = Location(
    id = id,
    name = name
)

fun AssetResponse.toDomain() = Asset(
    id = id ,
    name = name,
    status = status.toDomain(),
    location = location.toDomain(),
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun AssetStatusResponse.toDomain() = AssetStatus(
    status = status.toDomain(),
    count = count
)

fun AssetLocationResponse.toDomain() = AssetLocation(
    location = location.toDomain(),
    count = count
)

fun <T, R> PaginatedResponse<T>.toDomain(transform: (T) -> R): PaginatedData<R> = PaginatedData(
    items = items.map(transform),
    total = total,
    page = page,
    size = size,
    totalPages = totalPages
)