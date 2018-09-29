package com.github.rskupnik.thestory.shared

/**
 * Instances are "physical" entities in the game world, identified by a unique [id].
 *
 * The default [reference] of an [Instance] is its [id]. Instances are mostly created from [Blueprint]s,
 * which makes them [BlueprintInstance]s.
 */
interface Instance : Referable {

    val id: String

    override val reference: Reference
        get() = Reference.to(id)
}