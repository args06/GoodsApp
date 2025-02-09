package com.example.goodsapp.di.module

import com.example.goodsapp.domain.usecase.asset.CreateAssetUseCase
import com.example.goodsapp.domain.usecase.asset.CreateAssetUseCaseImpl
import com.example.goodsapp.domain.usecase.asset.GetAssetsByLocationUseCase
import com.example.goodsapp.domain.usecase.asset.GetAssetsByLocationUseCaseImpl
import com.example.goodsapp.domain.usecase.asset.GetAssetsByStatusUseCase
import com.example.goodsapp.domain.usecase.asset.GetAssetsByStatusUseCaseImpl
import com.example.goodsapp.domain.usecase.asset.GetAssetsUseCase
import com.example.goodsapp.domain.usecase.asset.GetAssetsUseCaseImpl
import com.example.goodsapp.domain.usecase.auth.GetCurrentUserUseCase
import com.example.goodsapp.domain.usecase.auth.GetCurrentUserUseCaseImpl
import com.example.goodsapp.domain.usecase.auth.IsUserLoggedInUseCase
import com.example.goodsapp.domain.usecase.auth.IsUserLoggedInUseCaseImpl
import com.example.goodsapp.domain.usecase.auth.LoginUseCase
import com.example.goodsapp.domain.usecase.auth.LoginUseCaseImpl
import com.example.goodsapp.domain.usecase.auth.LogoutUseCase
import com.example.goodsapp.domain.usecase.auth.LogoutUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindLoginUseCase(
        loginUseCaseImpl: LoginUseCaseImpl
    ): LoginUseCase

    @Binds
    fun bindGetAssetsUseCase(
        getAssetsUseCaseImpl: GetAssetsUseCaseImpl
    ): GetAssetsUseCase

    @Binds
    fun bindGetAssetsByStatusUseCase(
        getAssetsByStatusUseCaseImpl: GetAssetsByStatusUseCaseImpl
    ): GetAssetsByStatusUseCase

    @Binds
    fun bindGetAssetsByLocationUseCase(
        getAssetsByLocationUseCaseImpl: GetAssetsByLocationUseCaseImpl
    ): GetAssetsByLocationUseCase

    @Binds
    fun bindCreateAssetUseCase(
        createAssetUseCaseImpl: CreateAssetUseCaseImpl
    ): CreateAssetUseCase

    @Binds
    fun bindGetCurrentUserUseCase(
        getCurrentUserUseCaseImpl: GetCurrentUserUseCaseImpl
    ): GetCurrentUserUseCase

    @Binds
    fun bindIsUserLoggedInUseCase(
        isUserLoggedInUseCaseImpl: IsUserLoggedInUseCaseImpl
    ): IsUserLoggedInUseCase

    @Binds
    fun bindLogoutUseCase(
        logoutUseCaseImpl: LogoutUseCaseImpl
    ): LogoutUseCase

}