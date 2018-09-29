package com.github.rskupnik.thestory.shared.util

import com.github.rskupnik.thestory.shared.Blueprint
import com.github.rskupnik.thestory.shared.Instance
import com.github.rskupnik.thestory.shared.Mutator
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.external.FileLoader
import com.github.rskupnik.thestory.shared.external.JsonParser
import com.github.rskupnik.thestory.shared.json.BlueprintJsonRepresentation
import com.github.rskupnik.thestory.shared.persistence.Persistable
import com.github.rskupnik.thestory.shared.persistence.PersistableState
import kotlin.reflect.KClass

object CommonFacadeOperations {

    fun <E : Blueprint> loadBlueprints(moduleReference: Reference, fileLoader: FileLoader, definitionPath: String,
                                       jsonParser: JsonParser, jsonClass: KClass<out BlueprintJsonRepresentation<E>>): List<E> {
        val definition = fileLoader.getFileHandle(String.format(definitionPath, moduleReference.value))
                .let { fileLoader.loadAsString(it ?: return emptyList()) }
        return jsonParser.parseList(jsonClass.java, definition)
                .asSequence()
                .map { it.toBlueprint() }.toList()
    }

    fun <R : PersistableState> getPersistableState(persistables: List<Persistable<R>>): List<R> =
            persistables.map { it.toPersistableState() }.toList()

    fun <T : Mutator, R : Instance> mutate(instance: R, mutator: T): Boolean {
        mutator.mutate(instance)
        return true
    }
}