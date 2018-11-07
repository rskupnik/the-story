package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.external.asset.AssetLoader
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.external.file.FileSaver
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

    @Provides @Singleton
    fun callbackReceiver(): CallbackReceiver = implementationExchange.getImplementation(CallbackReceiver::class)

    @Provides @Singleton
    fun fileSaver(): FileSaver = implementationExchange.getImplementation(FileSaver::class)
}