package com.example.goodsapp.domain.model

data class PaginatedData<T>(
    val items: List<T>,
    val total: Int,
    val page: Int,
    val size: Int,
    val totalPages: Int
)