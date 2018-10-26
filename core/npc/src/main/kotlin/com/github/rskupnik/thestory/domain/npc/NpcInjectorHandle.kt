package com.github.rskupnik.thestory.domain.npc

import com.github.rskupnik.thestory.domain.npc.internal.NpcBlueprintRepository
import com.github.rskupnik.thestory.domain.npc.internal.NpcInstanceRepository
import com.github.rskupnik.thestory.domain.npc.internal.NpcServiceImplementation
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser

object NpcInjectorHandle {

    private val blueprintRepository = NpcBlueprintRepository()
    private val instanceRepository = NpcInstanceRepository()

    fun service(fileLoader: FileLoader, jsonParser: JsonParser): NpcService = NpcServiceImplementation(
            fileLoader, jsonParser, blueprintRepository, instanceRepository
    )
}