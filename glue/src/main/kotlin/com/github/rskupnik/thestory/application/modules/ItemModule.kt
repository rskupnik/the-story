package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.domain.item.ItemInjectorHandle
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ItemModule {

    @Provides @Singleton
    fun service(fileLoader: FileLoader, jsonParser: JsonParser, moduleService: ModuleService
    ): ItemService = ItemInjectorHandle.service(
            fileLoader, jsonParser, moduleService
    )
}