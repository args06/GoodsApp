package com.example.goodsapp.domain.usecase.base

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in P, R> {
    suspend operator fun invoke(parameters: P): Flow<Result<R>>
}

interface BaseUseCaseNoParams<R> {
    suspend operator fun invoke(): Flow<Result<R>>
}