package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.script.ScriptInjectorHandle
import com.github.rskupnik.thestory.script.ScriptService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScriptModule {

    @Provides @Singleton
    fun service(fileLoader: FileLoader, moduleService: ModuleService): ScriptService = ScriptInjectorHandle.script(
            fileLoader, moduleService)
}