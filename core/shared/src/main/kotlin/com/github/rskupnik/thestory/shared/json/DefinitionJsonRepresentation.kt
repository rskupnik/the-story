package com.github.rskupnik.thestory.shared.json

import com.github.rskupnik.thestory.shared.Definition

interface DefinitionJsonRepresentation<T : Definition> {
    fun toDefinition(): T
}