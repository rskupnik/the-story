package com.github.rskupnik.thestory.domain.`object`

import com.github.rskupnik.thestory.domain.option.Option
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.persistence.PersistableProvider

interface ObjectService : PersistableProvider {

    fun loadBlueprints(moduleReference: Reference)

    fun instantiate(id: String, blueprintReference: Reference): Reference?

    fun instantiateUnique(id: String, blueprintReference: Reference): Reference?

    fun getObjectView(reference: Reference): ObjectView?

    fun getOptions(reference: Reference): List<Option>

    fun mutate(reference: Reference, mutator: ObjectMutator): Boolean
}