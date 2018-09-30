package com.github.rskupnik.thestory.shared.json

import com.github.rskupnik.thestory.shared.Definition

interface DefinitionJsonRepresentation<T : Definition> {
    fun toDefinition(): T

    companion object {
        fun <R : Definition, T : DefinitionJsonRepresentation<R>> convertList(input: List<T>): List<R> =
                input.map(DefinitionJsonRepresentation<R>::toDefinition)
    }
}