package com.github.rskupnik.thestory.domain.module

import com.github.rskupnik.thestory.domain.module.internal.ModuleRepository
import com.github.rskupnik.thestory.domain.module.internal.ModuleServiceImplementation
import com.github.rskupnik.thestory.external.asset.AssetLoader
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser

object ModuleInjectorHandle {
    private val repository = ModuleRepository()

    fun service(fileLoader: FileLoader, assetLoader: AssetLoader, jsonParser: JsonParser): ModuleService = ModuleServiceImplementation(
            fileLoader, assetLoader, jsonParser, repository
    )
}