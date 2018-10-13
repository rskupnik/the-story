package com.github.rskupnik.thestory.domain.npc.injection

import com.github.rskupnik.thestory.domain.npc.NpcService
import com.github.rskupnik.thestory.domain.npc.internal.NpcBlueprintRepository
import com.github.rskupnik.thestory.domain.npc.internal.NpcInstanceRepository
import com.github.rskupnik.thestory.domain.npc.internal.NpcServiceImplementation
import com.github.rskupnik.thestory.shared.Blueprint
import com.github.rskupnik.thestory.shared.Instance
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser
import com.github.rskupnik.thestory.shared.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NpcModule {

    @Provides @Singleton
    fun service(fileLoader: FileLoader, jsonParser: JsonParser, blueprintRepository: Repository<Blueprint>,
                instanceRepository: Repository<Instance>): NpcService = NpcServiceImplementation(
            fileLoader, jsonParser, blueprintRepository as NpcBlueprintRepository,
            instanceRepository as NpcInstanceRepository
    )

    @Provides @Singleton
    fun blueprintRepository(): Repository<out Blueprint> = NpcBlueprintRepository()

    @Provides @Singleton
    fun instanceRepository(): Repository<out Instance> = NpcInstanceRepository()
}