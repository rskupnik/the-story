package com.github.rskupnik.thestory.util

import com.github.rskupnik.thestory.domain.shared.Blueprint
import com.github.rskupnik.thestory.domain.shared.Reference
import com.github.rskupnik.thestory.external.FileLoader
import com.github.rskupnik.thestory.external.JsonParser

object CommonFacadeOperations {

    fun <E : Blueprint> loadBlueprints(moduleReference: Reference, fileLoader: FileLoader, definitionPath: String,
                                       jsonParser: JsonParser, jsonRepresentationClass: Class<Any>): List<E> {
        TODO()
    }
}