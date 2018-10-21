package com.github.rskupnik.thestory.script

import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.script.internal.DefaultScriptService
import com.github.rskupnik.thestory.shared.external.file.FileLoader

object ScriptInjectorHandle {
    fun script(fileLoader: FileLoader, moduleService: ModuleService): ScriptService =
            DefaultScriptService(fileLoader, moduleService)
}