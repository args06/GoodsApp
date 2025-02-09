package com.example.goodsapp.data.repository

import com.example.goodsapp.data.local.UserPreferencesManager
import com.example.goodsapp.di.qualifier.IoDispatcher
import com.example.goodsapp.domain.model.User
import com.example.goodsapp.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun getCurrentUser(): Flow<Result<User?>> = flow {
        try {
            val user = userPreferencesManager.getUserFlow().first()
            emit(Result.success(user))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(ioDispatcher)

    override suspend fun isUserLoggedIn(): Flow<Result<Boolean>> = flow {
        try {
            val isLoggedIn = userPreferencesManager.isUserLoggedIn().first()
            emit(Result.success(isLoggedIn))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(ioDispatcher)

    override suspend fun logout(): Flow<Result<Boolean>> = flow {
        try {
            userPreferencesManager.clearUser()
            emit(Result.success(true))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(ioDispatcher)
}