package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.external.file.FileSaver
import com.github.rskupnik.thestory.persistence.PersistenceInjectorHandle
import com.github.rskupnik.thestory.persistence.PersistenceService
import com.github.rskupnik.thestory.persistence.PersistenceSubscriber
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class PersistenceModule {

    private val handle = PersistenceInjectorHandle()

    @Provides @Singleton
    fun service(
            fileSaver: FileSaver,
            fileLoader: FileLoader,
            moduleService: ModuleService
    ): PersistenceService = handle.service(fileSaver, fileLoader, moduleService)

    @Provides @Singleton
    fun subscriber(
            fileSaver: FileSaver,
            fileLoader: FileLoader,
            moduleService: ModuleService
    ): PersistenceSubscriber = handle.subscriber(fileSaver, fileLoader, moduleService)
}