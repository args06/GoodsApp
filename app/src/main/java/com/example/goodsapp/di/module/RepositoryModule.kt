package com.example.goodsapp.di.module

import com.example.goodsapp.data.local.UserPreferencesManager
import com.example.goodsapp.data.remote.api.GoodsApi
import com.example.goodsapp.data.repository.GoodsRepositoryImpl
import com.example.goodsapp.data.repository.UserRepositoryImpl
import com.example.goodsapp.di.qualifier.IoDispatcher
import com.example.goodsapp.domain.repository.GoodsRepository
import com.example.goodsapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGoodsRepository(
        kspApi: GoodsApi,
        userPreferencesManager: UserPreferencesManager,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): GoodsRepository {
        return GoodsRepositoryImpl(kspApi, userPreferencesManager, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userPreferencesManager: UserPreferencesManager,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): UserRepository = UserRepositoryImpl(userPreferencesManager, ioDispatcher)
}