package com.github.rskupnik.thestory.domain.`object`

import com.github.rskupnik.thestory.domain.`object`.internal.ObjectBlueprintRepository
import com.github.rskupnik.thestory.domain.`object`.internal.ObjectInstanceRepository
import com.github.rskupnik.thestory.domain.`object`.internal.ObjectServiceImplementation
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.persistence.PersistenceSubscriber
import com.github.rskupnik.thestory.shared.json.JsonParser

object ObjectInjectorHandle {

    private val blueprintRepository = ObjectBlueprintRepository()
    private val instanceRepository = ObjectInstanceRepository()

    fun service(
            fileLoader: FileLoader,
            jsonParser: JsonParser,
            persistenceSubscriber: PersistenceSubscriber
    ): ObjectService = ObjectServiceImplementation(
            fileLoader, jsonParser, blueprintRepository, instanceRepository, persistenceSubscriber
    )
}