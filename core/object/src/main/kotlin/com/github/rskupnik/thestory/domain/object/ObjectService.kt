package com.github.rskupnik.thestory.domain.`object`

import com.github.rskupnik.thestory.option.domain.Option
import com.github.rskupnik.thestory.persistence.Persister
import com.github.rskupnik.thestory.shared.Reference

interface ObjectService : Persister {

    fun loadBlueprints(moduleReference: Reference)

    fun instantiate(id: String, blueprintReference: Reference): Reference?

    fun instantiateUnique(id: String, blueprintReference: Reference): Reference?

    fun getObjectView(reference: Reference): ObjectView?

    fun getOptions(reference: Reference): List<Option>

    fun mutate(reference: Reference, mutator: ObjectMutator): Boolean
}