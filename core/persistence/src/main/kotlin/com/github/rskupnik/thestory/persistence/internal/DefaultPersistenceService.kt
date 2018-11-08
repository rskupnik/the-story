package com.github.rskupnik.thestory.persistence.internal

import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.external.file.FileSaver
import com.github.rskupnik.thestory.persistence.PersistenceService
import com.github.rskupnik.thestory.persistence.PersistenceSubscriber
import com.github.rskupnik.thestory.persistence.Persister

typealias State = Map<String, Any?>

internal class DefaultPersistenceService(
        private val fileSaver: FileSaver,
        private val fileLoader: FileLoader,
        private val moduleService: ModuleService
) : PersistenceService, PersistenceSubscriber {

    private val persisters: MutableSet<Persister> = HashSet()

    override fun subscribe(persister: Persister) {
        persisters.add(persister)
    }

    override fun save() {
        val module = requireNotNull(moduleService.getLoadedStandaloneModule())
        val snapshot: MutableMap<String, Any?> = HashMap()

        // TODO: GameState and PlayerData

        persisters.forEach { snapshot[it.persistenceKey] = it.produceState() }

        fileSaver.saveSnapshot("${module.id}.sav", snapshot)
    }

    override fun readState(filename: String): State = requireNotNull(fileLoader.loadSnapshot(filename))

    override fun loadState(state: State) = persisters.forEach {
        it.ingestState(state[it.persistenceKey] ?: return)
    }
}