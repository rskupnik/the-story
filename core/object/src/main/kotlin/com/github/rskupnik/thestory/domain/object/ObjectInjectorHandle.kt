package com.github.rskupnik.thestory.domain.`object`

import com.github.rskupnik.thestory.domain.`object`.internal.ObjectBlueprintRepository
import com.github.rskupnik.thestory.domain.`object`.internal.ObjectInstanceRepository
import com.github.rskupnik.thestory.domain.`object`.internal.ObjectServiceImplementation
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser

object ObjectInjectorHandle {

    private val blueprintRepository = ObjectBlueprintRepository()
    private val instanceRepository = ObjectInstanceRepository()

    fun service(fileLoader: FileLoader, jsonParser: JsonParser): ObjectService = ObjectServiceImplementation(
            fileLoader, jsonParser, blueprintRepository, instanceRepository
    )
}