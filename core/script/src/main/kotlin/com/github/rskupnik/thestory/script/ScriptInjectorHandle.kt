package com.github.rskupnik.thestory.script

import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.script.internal.DefaultScriptService

object ScriptInjectorHandle {
    fun script(fileLoader: FileLoader, moduleService: ModuleService): ScriptService =
            DefaultScriptService(fileLoader, moduleService)
}