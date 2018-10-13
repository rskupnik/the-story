package com.github.rskupnik.thestory.domain.item.injection

import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.domain.item.internal.ItemBlueprintRepository
import com.github.rskupnik.thestory.domain.item.internal.ItemInstanceRepository
import com.github.rskupnik.thestory.domain.item.internal.ItemServiceImplementation
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.shared.Blueprint
import com.github.rskupnik.thestory.shared.Instance
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser
import com.github.rskupnik.thestory.shared.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ItemModule {

    @Provides @Singleton
    fun service(fileLoader: FileLoader, jsonParser: JsonParser, moduleService: ModuleService,
                blueprintRepository: Repository<Blueprint>, instanceRepository: Repository<Blueprint>
    ): ItemService = ItemServiceImplementation(
            fileLoader, jsonParser, moduleService, blueprintRepository as ItemBlueprintRepository,
            instanceRepository as ItemInstanceRepository
    )

    @Provides @Singleton
    fun blueprintRepository(): Repository<out Blueprint> = ItemBlueprintRepository()

    @Provides @Singleton
    fun instanceRepository(): Repository<out Instance> = ItemInstanceRepository()
}