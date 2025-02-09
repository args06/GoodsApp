package com.example.goodsapp.domain.usecase.auth

import com.example.goodsapp.domain.repository.UserRepository
import com.example.goodsapp.domain.usecase.base.BaseUseCaseNoParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IsUserLoggedInUseCase : BaseUseCaseNoParams<Boolean>

class IsUserLoggedInUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : IsUserLoggedInUseCase {
    override suspend fun invoke(): Flow<Result<Boolean>> =
        repository.isUserLoggedIn()
}