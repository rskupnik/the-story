package com.github.rskupnik.thestory.shared.util

import com.github.rskupnik.thestory.shared.Instance
import com.github.rskupnik.thestory.shared.Mutator
import com.github.rskupnik.thestory.shared.persistence.Persistable
import com.github.rskupnik.thestory.shared.persistence.PersistableState

object CommonFacadeOperations {

    fun <R : PersistableState> getPersistableState(persistables: List<Persistable<R>>): List<R> =
            persistables.map { it.toPersistableState() }.toList()

    fun <T : Mutator, R : Instance> mutate(instance: R, mutator: T): Boolean {
        mutator.mutate(instance)
        return true
    }
}