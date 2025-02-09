package com.example.goodsapp.domain.repository

import com.example.goodsapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getCurrentUser(): Flow<Result<User?>>
    suspend fun isUserLoggedIn(): Flow<Result<Boolean>>
    suspend fun logout(): Flow<Result<Boolean>>
}