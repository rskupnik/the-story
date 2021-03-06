package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.application.delegates.ModuleServiceDelegate
import com.github.rskupnik.thestory.application.internal.Internals
import com.github.rskupnik.thestory.domain.module.ModuleInjectorHandle
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.asset.AssetLoader
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModulesModule {

    @Provides @Singleton
    fun service(
            internals: Internals,
            fileLoader: FileLoader,
            assetLoader: AssetLoader,
            jsonParser: JsonParser
    ): ModuleService = internals.getOrCreateDelegate(ModuleService::class) {
        ModuleServiceDelegate(ModuleInjectorHandle.service(fileLoader, assetLoader, jsonParser))
    }
}