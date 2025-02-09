package com.example.goodsapp.domain.usecase.auth

import com.example.goodsapp.domain.repository.UserRepository
import com.example.goodsapp.domain.usecase.base.BaseUseCaseNoParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LogoutUseCase : BaseUseCaseNoParams<Boolean>

class LogoutUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : LogoutUseCase {
    override suspend fun invoke(): Flow<Result<Boolean>> =
        repository.logout()
}