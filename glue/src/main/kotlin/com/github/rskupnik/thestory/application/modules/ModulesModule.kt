package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.domain.module.ModuleInjectorHandle
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.shared.external.asset.AssetLoader
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModulesModule {

    @Provides @Singleton
    fun service(fileLoader: FileLoader, assetLoader: AssetLoader, jsonParser: JsonParser): ModuleService =
            ModuleInjectorHandle.service(fileLoader, assetLoader, jsonParser)
}