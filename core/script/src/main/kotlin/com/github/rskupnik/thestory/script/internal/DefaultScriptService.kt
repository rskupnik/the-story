package com.github.rskupnik.thestory.script.internal

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.script.Script
import com.github.rskupnik.thestory.script.ScriptService
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import com.github.rskupnik.wordplay.WordplayImpl
import com.github.rskupnik.wordplay.output.WordplayOutput

internal class DefaultScriptService(
        private val fileLoader: FileLoader,
        private val moduleService: ModuleService
) : ScriptService {

    private val wordplay = WordplayImpl()

    override fun loadLocation(locationId: LocationId): WordplayOutput? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun parse(input: WordplayOutput): Script {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}