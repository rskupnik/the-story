package com.github.rskupnik.thestory.shared

/**
 * Represents [Instance]s created from [Blueprint]s.
 *
 * It holds a reference to the [blueprint] the [Instance] was created from.
 */
interface BlueprintInstance<B : Blueprint> : Instance {
    val blueprint: B
}