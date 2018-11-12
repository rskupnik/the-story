package com.github.rskupnik.thestory.persistence

import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.external.file.FileSaver
import com.github.rskupnik.thestory.persistence.internal.DefaultPersistenceService

class PersistenceInjectorHandle {

    private var persistenceService: DefaultPersistenceService? = null

    fun service(
            fileSaver: FileSaver,
            fileLoader: FileLoader,
            moduleService: ModuleService
    ): PersistenceService = default(fileSaver, fileLoader, moduleService)

    fun subscriber(
            fileSaver: FileSaver,
            fileLoader: FileLoader,
            moduleService: ModuleService
    ): PersistenceSubscriber = default(fileSaver, fileLoader, moduleService)

    private fun default(
            fileSaver: FileSaver,
            fileLoader: FileLoader,
            moduleService: ModuleService
    ): DefaultPersistenceService = if (persistenceService != null) persistenceService!! else {
        persistenceService = DefaultPersistenceService(fileSaver, fileLoader, moduleService)
        persistenceService!!
    }
}