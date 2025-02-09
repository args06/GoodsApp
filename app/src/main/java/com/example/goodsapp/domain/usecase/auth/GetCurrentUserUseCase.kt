package com.example.goodsapp.domain.usecase.auth

import com.example.goodsapp.domain.model.User
import com.example.goodsapp.domain.repository.UserRepository
import com.example.goodsapp.domain.usecase.base.BaseUseCaseNoParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCurrentUserUseCase : BaseUseCaseNoParams<User?>

class GetCurrentUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetCurrentUserUseCase {
    override suspend fun invoke(): Flow<Result<User?>> =
        repository.getCurrentUser()
}