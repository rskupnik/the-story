package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.shared.external.asset.AssetLoader
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class AdaptersModule {

    private val implementationExchange = ImplementationExchange()

    @Provides @Singleton
    fun implementationProvider(): ImplementationProvider = implementationExchange

    @Provides @Singleton
    fun fileLoader(): FileLoader = implementationExchange.getImplementation(FileLoader::class)

    @Provides @Singleton
    fun assetLoader(): AssetLoader = implementationExchange.getImplementation(AssetLoader::class)
}