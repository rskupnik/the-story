package com.github.rskupnik.thestory.script.internal

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.script.Script
import com.github.rskupnik.thestory.script.ScriptParser
import com.github.rskupnik.thestory.script.ScriptService
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import com.github.rskupnik.wordplay.WordplayImpl
import com.github.rskupnik.wordplay.output.WordplayOutput

const val PATH = "modules/unpacked/%s/zones/%s/%s"

internal class DefaultScriptService(
        private val fileLoader: FileLoader,
        private val moduleService: ModuleService
) : ScriptService {

    private val wordplay = WordplayImpl()
    private val scriptParser = ScriptParser()

    override fun loadLocation(locationId: LocationId): WordplayOutput? =
            moduleService.getLoadedModules()
                    .asSequence()
                    .map { fileLoader.getFileHandle(String.format(PATH, it.id, locationId.zone, ""+locationId.x+","+locationId.y+".wp")) }
                    .filterNotNull()
                    .first()
                    .let { fileLoader.loadAsString(it) }
                    .let { wordplay.process(it) }

    override fun parse(input: WordplayOutput): Script = scriptParser.parse(input)
}