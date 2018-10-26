package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.domain.npc.NpcInjectorHandle
import com.github.rskupnik.thestory.domain.npc.NpcService
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NpcModule {

    @Provides @Singleton
    fun service(fileLoader: FileLoader, jsonParser: JsonParser): NpcService = NpcInjectorHandle.service(
            fileLoader, jsonParser
    )
}