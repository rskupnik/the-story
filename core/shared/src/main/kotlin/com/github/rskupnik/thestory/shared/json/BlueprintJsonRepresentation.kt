package com.github.rskupnik.thestory.shared.json

import com.github.rskupnik.thestory.shared.Blueprint

/**
 * A marker interface for classes that are json representations of [Blueprint]s.
 */
interface BlueprintJsonRepresentation<B : Blueprint> {
    fun toBlueprint(): B
}