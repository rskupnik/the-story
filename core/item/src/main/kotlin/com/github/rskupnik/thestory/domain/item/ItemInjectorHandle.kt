package com.github.rskupnik.thestory.domain.item

import com.github.rskupnik.thestory.domain.item.internal.ItemBlueprintRepository
import com.github.rskupnik.thestory.domain.item.internal.ItemInstanceRepository
import com.github.rskupnik.thestory.domain.item.internal.ItemServiceImplementation
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.persistence.PersistenceSubscriber
import com.github.rskupnik.thestory.shared.json.JsonParser

object ItemInjectorHandle {

    private val blueprintRepository = ItemBlueprintRepository()
    private val instanceRepository = ItemInstanceRepository()

    fun service(
            fileLoader: FileLoader,
            jsonParser: JsonParser,
            moduleService: ModuleService,
            persistenceSubscriber: PersistenceSubscriber
    ): ItemService =
            ItemServiceImplementation(
                    fileLoader, jsonParser, moduleService, blueprintRepository, instanceRepository, persistenceSubscriber
            )
}