package com.example.goodsapp.domain.model

data class Asset(
    val id: String,
    val name: String,
    val status: Status,
    val location: Location,
    val createdAt: String,
    val updatedAt: String
)