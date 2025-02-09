package com.example.goodsapp.base

import android.app.Application
import com.example.goodsapp.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class GoodsApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}