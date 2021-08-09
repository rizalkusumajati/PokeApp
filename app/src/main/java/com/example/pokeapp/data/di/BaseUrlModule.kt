package com.example.pokeapp.data.di

import com.example.pokeapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {
    @Singleton
    @Provides
    fun provideBaseUrl() : String = BuildConfig.BASE_URL
}
