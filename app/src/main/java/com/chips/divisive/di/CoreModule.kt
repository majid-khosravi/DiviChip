package com.chips.divisive.di

import com.chips.divisive.model.GlobalDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * In this module I've implemented some common provider,
 *  for instance I've defined the [GlobalDispatcher] provider to use in other modules
 */


@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun provideGlobalDispatcher() = GlobalDispatcher(
        main = Dispatchers.Main, io = Dispatchers.IO, default = Dispatchers.Default
    )


}