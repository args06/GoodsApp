package com.example.goodsapp.domain.usecase.auth

import com.example.goodsapp.domain.model.User
import com.example.goodsapp.domain.repository.GoodsRepository
import com.example.goodsapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface LoginUseCase : BaseUseCase<LoginUseCase.Params, User> {
    data class Params(
        val email: String,
        val password: String
    )
}

class LoginUseCaseImpl @Inject constructor(
    private val repository: GoodsRepository
) : LoginUseCase {

    override suspend fun invoke(parameters: LoginUseCase.Params): Flow<Result<User>> =
        repository.login(parameters.email, parameters.password)
}