package com.github.rskupnik.thestory.shared.util

import com.github.rskupnik.thestory.shared.Instance
import com.github.rskupnik.thestory.shared.Mutator

object CommonFacadeOperations {

    fun <T : Mutator, R : Instance> mutate(instance: R, mutator: T): Boolean {
        mutator.mutate(instance)
        return true
    }
}